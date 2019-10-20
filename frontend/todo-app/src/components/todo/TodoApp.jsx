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
import { SignUpComponent } from "../account/SignUpComponent";
import MapComponent from "../map/MapComponent";
import AuthenticationService from "./AuthenticationService";
import Grid from "@material-ui/core/Grid";
import { ListProduct } from "../product/ListProduct";

import ProductsService from "../product/ProductsService.js";
import * as lodash from "lodash";

export class TodoApp extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isUserLoggedIn: AuthenticationService.isUserLoggedIn(),
      cards: [],
      cart: [],
      cartEmpty: true
    };

    this.handleClearCart = this.handleClearCart.bind(this);
    this.getProducts = this.getProducts.bind(this);
  }

  componentDidUpdate(prevProps, prevState) {
    if (this.props.location.pathname !== prevProps.location.pathname) {
      this.checkIfUserLoggedIn();
      this.getProducts();
    }
  }

  getProducts() {
    //retrieve the products from the backend and store in state
    ProductsService.retrieveProducts()
      .then(response => {
        const formattedCards = response.data.map((card, index) => {
          // we return a new card with an index starting from 0 so that we can load the images
          return {
            ...card,
            id: index
          };
        });

        this.setState({ cards: formattedCards });
      })
      .catch(error => {
        if (error.response) {
          this.setState({ cards: error.response.data });
        } else if (error.request) {
        } else {
          console.log(error.message);
        }
      });
  }

  checkIfUserLoggedIn = () => {
    this.setState({ isUserLoggedIn: AuthenticationService.isUserLoggedIn() });
  };

  //Add product to cart array, set from child ProductComponent's prop handler
  handleAddToCart = product => {
    this.setState({ cart: [...this.state.cart, product], cartEmpty: false });
  };

  handleClearCart = () => {
    this.setState({ cart: [], cartEmpty: true });
  };

  sortProduct = cards => {
    if (!cards) {
      return undefined;
    }
    //if any product does not have a name, we do not run this function further
    for (const card of cards) {
      if (!card.productName) {
        return undefined;
      }
    }
    //get all names of the different products
    const nameOfProducts = cards.map(card => {
      return card.productName;
    });
    let arraySortedDesc = false;
    //this loop checks whether the array is sorted in descending order
    for (let i = 0; i < nameOfProducts.length; i++) {
      if (i + 1 === nameOfProducts.length) {
        break;
      }
      if (nameOfProducts[i] < nameOfProducts[i + 1]) {
        continue;
      }
      arraySortedDesc = true;
    }

    const newCards = arraySortedDesc
      ? lodash.orderBy(cards, ["productName"], ["asc"])
      : lodash.orderBy(cards, ["productName"], ["desc"]);
    this.setState({ cards: newCards });
    return newCards;
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
                    sortProduct={this.sortProduct}
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
                    empty={this.state.cartEmpty}
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
              <AuthenticatedRoute
                path="/list-product"
                component={ListProduct}
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
