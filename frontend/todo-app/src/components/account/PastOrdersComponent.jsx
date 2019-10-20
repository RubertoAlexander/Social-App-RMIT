import * as React from "react";
import ProductsService from "../product/ProductsService";
import Grid from "@material-ui/core/Grid";
import Card from "@material-ui/core/Card";

export class PastOrdersComponent extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      products: [
        {
          id: 0,
          date: "",

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
      return product.lineItems ? (
        <React.Fragment key={key}>
          <Grid item xs={12} sm={6}>
            <Card>
              <div>Name: {product.lineItems[0].product.productName}</div>
              <div>Price: {product.lineItems[0].product.price}</div>
              <div>Date purchased: {product.date}</div>
            </Card>
          </Grid>
        </React.Fragment>
      ) : (
        <React.Fragment></React.Fragment>
      );
    });

    return (
      <Grid container justify="center" alignItems="center">
        {productDisplay}
      </Grid>
    );
  }
}
