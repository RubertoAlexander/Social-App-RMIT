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
import MapComponent from "../map/MapComponent.jsx";
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
import product10 from "../../img/product (10).jpg";
import product11 from "../../img/product (11).jpg";
import product12 from "../../img/product (12).jpg";
import product13 from "../../img/product (13).jpg";

class TodoApp extends Component {
  constructor(props) {
    super(props);
    this.state = {
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

    this.handleAddToCart = this.handleAddToCart.bind(this);
  }

  //Add product to cart array, set from child ProductComponent's prop handler
  handleAddToCart(product) {
    this.setState({ cart: [...this.state.cart, product] });
  }

  render() {
    return (
      <div className="TodoApp">
        <Router>
          <React.Fragment>
            <HeaderComponent cart={this.state.cart} />
            <Switch>
              <Route
                exact
                path="/"
                render={props => (
                  <ProductComponent
                    cards={this.state.cards}
                    handleAddToCart={this.handleAddToCart}
                  />
                )}
              />

              <Route path="/login" component={LoginComponent} />

              <AuthenticatedRoute
                path="/cart"
                render={props => <CartComponent cart={this.state.cart} />}
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
