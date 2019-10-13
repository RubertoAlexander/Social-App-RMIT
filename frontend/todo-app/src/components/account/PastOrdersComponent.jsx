import * as React from "react";
import ProductsService from "../product/ProductsService";

export class PastOrdersComponent extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      products: [
        {
          id: 0,
          date: "",
          lineItems: [{}],
          totalPrice: 0
        }
      ]
    };
  }

  componentDidMount() {
    //fetch users order and update state
    const userID = sessionStorage.getItem("user_id");
    const products = ProductsService.retrievePastOrders(userID).then(
      response => {
        this.setState({ products: response.data });
      }
    );
  }

  render() {
    const productDisplay = this.state.products.map((product, key) => {
      return (
        <React.Fragment key={key}>
          <div>Name: {product.lineItems[0].product.productName}</div>
          <div>price: {product.lineItems[0].product.price}</div>
          <div>date purchased: {product.date}</div>
        </React.Fragment>
      );
    });

    return <div>{productDisplay}</div>;
  }
}
