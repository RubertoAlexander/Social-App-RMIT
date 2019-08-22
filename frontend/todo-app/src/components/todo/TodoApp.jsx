import React, { Component } from "react";
import { Route, Switch, withRouter } from "react-router-dom";
import AuthenticatedRoute from "./AuthenticatedRoute.jsx";
import LoginComponent from "../account/LoginComponent.jsx";
import ListTodosComponent from "./ListTodosComponent.jsx";
import ErrorComponent from "./ErrorComponent.jsx";
import HeaderComponent from "./HeaderComponent.jsx";
import FooterComponent from "./FooterComponent.jsx";
import LogoutComponent from "./LogoutComponent.jsx";
import WelcomeComponent from "./WelcomeComponent.jsx";
import TodoComponent from "./TodoComponent.jsx";
import ProductComponent from "../product/ProductComponent";
import { ProductDetailComponent } from "../product/ProductDetailComponent";
import { SignUpComponent } from "../account/SignUpComponent";
import MapComponent from "../map/MapComponent";
import AuthenticationService from "./AuthenticationService";
import Grid from "@material-ui/core/Grid";

class TodoApp extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isUserLoggedIn: AuthenticationService.isUserLoggedIn()
    };
  }

  componentDidUpdate(prevProps) {
    if (this.props.location.pathname !== prevProps.location.pathname) {
      this.checkIfUserLoggedIn();
    }
  }

  checkIfUserLoggedIn = () => {
    this.setState({ isUserLoggedIn: AuthenticationService.isUserLoggedIn() });
  };

  render() {
    return (
      <React.Fragment>
        <Grid container>
          <Grid xs={12}>
            <HeaderComponent isUserLoggedIn={this.state.isUserLoggedIn} />
          </Grid>
          <Grid xs={12}>
            <Switch>
              <Route path="/" exact component={ProductComponent} />
              <Route path="/product/:id" component={ProductDetailComponent} />

              <Route path="/login" component={LoginComponent} />
              <AuthenticatedRoute
                path="/welcome/:name"
                component={WelcomeComponent}
              />
              <AuthenticatedRoute path="/todos/:id" component={TodoComponent} />
              <AuthenticatedRoute
                path="/todos"
                component={ListTodosComponent}
              />
              <Route
                path="/sign-up"
                render={props => <SignUpComponent {...props} />}
              />
              <AuthenticatedRoute path="/map" component={MapComponent} />
              <AuthenticatedRoute path="/logout" component={LogoutComponent} />

              <Route path="/product/:id" component={ProductDetailComponent} />

              <Route component={ErrorComponent} />
            </Switch>
          </Grid>
          <Grid xs={12}>
            <FooterComponent />
          </Grid>
        </Grid>
      </React.Fragment>
    );
  }
}

export default withRouter(TodoApp);
