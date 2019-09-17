import React from "react";
import {
  Avatar,
  Container,
  Divider,
  List,
  ListItem,
  ListItemAvatar,
  ListItemText,
  Paper,
  Typography,
  Button
} from "@material-ui/core";

export default class CartComponent extends React.Component {
  constructor(props) {
    super(props);

    this.state = { empty: this.props.empty };
  }

  handleClearCart = () => {
    this.setState({ empty: true });
    this.props.handleClearCart();
  };

  items = () => {
    if (this.state.empty) {
      return (
        <Container className="empty-msg">
          <Typography variant="h3">You're cart is empty</Typography>
        </Container>
      );
    } else
      return (
        <Container>
          <Typography variant="h5" gutterBottom>
            Cart
          </Typography>
          <List disablePadding>
            {this.props.cart.map((product, index) => (
              <React.Fragment key={index}>
                <ListItem className="cart-item">
                  <ListItemAvatar>
                    <Avatar alt={product.name} src={product.imageUrl} />
                  </ListItemAvatar>
                  <ListItemText
                    key={product.id}
                    primary={product.name}
                    secondary={product.description}
                  ></ListItemText>
                </ListItem>
                <Divider />
              </React.Fragment>
            ))}
          </List>
          <Button
            className="clear-cart-but"
            type="reset"
            variant="contained"
            onClick={this.handleClearCart}
          >
            Clear Cart
          </Button>
        </Container>
      );
  };

  render() {
    return (
      <React.Fragment>
        <Paper max-width="sm">{this.items()}</Paper>
      </React.Fragment>
    );
  }
}
