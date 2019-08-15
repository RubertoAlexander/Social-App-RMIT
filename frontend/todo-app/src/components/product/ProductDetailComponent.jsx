import * as React from "react";

export class ProductDetailComponent extends React.Component {
  constructor(props) {
    super(props);
    this.productId = props.match.params.id;
  }

  render() {
    return <div>{this.productId}</div>;
  }
}
