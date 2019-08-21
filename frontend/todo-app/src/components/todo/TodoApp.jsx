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
  constructor(props) {
    super(props);
    this.state = {
      cards: [
        {
          id: 1,
          name: "Programmer Guide",
          description: "blablablablabla"
        },
        {
          id: 2,
          name: "Elephant Book",
          description: "blablablablabla"
        },
        {
          id: 3,
          name: "Self taught programmer",
          description: "blablablablabla"
        },
        {
          id: 4,
          name: "Computer Science Book",
          description: "blablablablabla"
        },
        {
          id: 5,
          name: "Beginning Programming Reference for dummies",
          description: "blablablablabla"
        },
        {
          id: 6,
          name: "Computer Science Distilled",
          description: "blablablablabla"
        },
        {
          id: 7,
          name: "Computer science principles",
          description: "blablablablabla"
        },
        {
          id: 8,
          name: "Structure and Interpretation of Computer Programs",
          description: "blablablablabla"
        },
        {
          id: 9,
          name: "AP Computer Science A",
          description: "blablablablabla"
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
