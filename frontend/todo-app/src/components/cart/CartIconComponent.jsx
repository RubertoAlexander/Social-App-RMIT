import React from "react";
import ShoppingCartIcon from "@material-ui/icons/ShoppingCart";

export class CartIconComponent extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <ShoppingCartIcon />
        <span>{this.props.cartTotal}</span>
      </div>
    );
  }
}
