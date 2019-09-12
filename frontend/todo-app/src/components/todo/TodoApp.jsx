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

import CartComponent from "../cart/CartComponent.jsx";

import product1 from "../../img/product (1).jpg";
import product2 from "../../img/product (2).jpg";
import product3 from "../../img/product (3).jpg";
import product4 from "../../img/product (4).jpg";
import product5 from "../../img/product (5).jpg";
import product6 from "../../img/product (6).jpg";
import product7 from "../../img/product (7).jpg";
import product8 from "../../img/product (8).jpg";
import product9 from "../../img/product (9).jpg";
import { SignUpComponent } from "../account/SignUpComponent";
import MapComponent from "../map/MapComponent";
import AuthenticationService from "./AuthenticationService";
import Grid from "@material-ui/core/Grid";

class TodoApp extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isUserLoggedIn: AuthenticationService.isUserLoggedIn(),
      cards: [
        {
          id: 1,
          name: "Programmer Guide",
          description: "blablablablabla",
          imageUrl: product1
        },
        {
          id: 2,
          name: "Elephant Book",
          description: "blablablablabla",
          imageUrl: product2
        },
        {
          id: 3,
          name: "Self taught programmer",
          description: "blablablablabla",
          imageUrl: product3
        },
        {
          id: 4,
          name: "Computer Science Book",
          description: "blablablablabla",
          imageUrl: product4
        },
        {
          id: 5,
          name: "Beginning Programming Reference for dummies",
          description: "blablablablabla",
          imageUrl: product5
        },
        {
          id: 6,
          name: "Computer Science Distilled",
          description: "blablablablabla",
          imageUrl: product6
        },
        {
          id: 7,
          name: "Computer science principles",
          description: "blablablablabla",
          imageUrl: product7
        },
        {
          id: 8,
          name: "Structure and Interpretation of Computer Programs",
          description: "blablablablabla",
          imageUrl: product8
        },
        {
          id: 9,
          name: "AP Computer Science A",
          description: "blablablablabla",
          imageUrl: product9
        }
      ],
      cart: []
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

  //Add product to cart array, set from child ProductComponent's prop handler
  handleAddToCart = product => {
    this.setState({ cart: [...this.state.cart, product] });
  };

  handleClearCart = () => {
    this.setState({ cart: [] });
  };

  render() {
    return (
      <React.Fragment>
        <Grid container>
          <Grid item xs={12}>
            <HeaderComponent
              cart={this.state.cart}
              isUserLoggedIn={this.state.isUserLoggedIn}
            />
          </Grid>
          <Grid item xs={12}>
            <Switch>
              <Route
                path="/"
                exact
                render={props => (
                  <ProductComponent
                    cards={this.state.cards}
                    handleAddToCart={this.handleAddToCart}
                  />
                )}
              />
              <Route path="/product/:id" component={ProductDetailComponent} />

              <Route path="/login" component={LoginComponent} />

              <AuthenticatedRoute
                path="/cart"
                render={props => (
                  <CartComponent
                    cart={this.state.cart}
                    handleClearCart={this.handleClearCart}
                  />
                )}
              />

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
          <Grid item xs={12}>
            <FooterComponent />
          </Grid>
        </Grid>
      </React.Fragment>
    );
  }
}

export default withRouter(TodoApp);
