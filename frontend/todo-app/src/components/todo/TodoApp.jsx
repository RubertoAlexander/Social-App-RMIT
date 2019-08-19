import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AuthenticatedRoute from "./AuthenticatedRoute.jsx";
import LoginComponent from "./LoginComponent.jsx";
import ListTodosComponent from "./ListTodosComponent.jsx";
import ErrorComponent from "./ErrorComponent.jsx";
import HeaderComponent from "./HeaderComponent.jsx";
import FooterComponent from "./FooterComponent.jsx";
import LogoutComponent from "./LogoutComponent.jsx";
import WelcomeComponent from "./WelcomeComponent.jsx";
import TodoComponent from "./TodoComponent.jsx";
import ProductComponent from "../product/ProductComponent";
import { ProductDetailComponent } from "../product/ProductDetailComponent";
import MapComponent from "../map/MapComponent";

class TodoApp extends Component {
  render() {
    return (
      <div className="TodoApp">
        <Router>
          <React.Fragment>
            <HeaderComponent />
            <Switch>
              <Route path="/" exact component={ProductComponent} />
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
              <AuthenticatedRoute path="/map" component={MapComponent} />
              <AuthenticatedRoute path="/logout" component={LogoutComponent} />

              <Route path="/product/:id" component={ProductDetailComponent} />

              <Route component={ErrorComponent} />
            </Switch>
            <FooterComponent />
          </React.Fragment>
        </Router>
        {/*<LoginComponent/>
                <WelcomeComponent/>*/}
      </div>
    );
  }
}

export default TodoApp;
