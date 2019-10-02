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
  Button,
  withStyles,
  Grid
} from "@material-ui/core";

import CartService from "../cart/CartService";

const styles = theme => ({
  cartPaper: {
    display: "block",
    margin: "5% 20% 5% 20%"
  },
  cartItems: {
    marginBottom: "5%"
  },
  cartTitle: {
    paddingTop: "5%"
  },
  emptyMsg: {
    paddingTop: "10%",
    paddingBottom: "10%",
    justifySelf: "center"
  },
  BuyBut: {
    marginBottom: "5%",
    marginTop: "5%"
  },
  clearCartBut: {
    marginTop: "5%",
    marginBottom: "5%"
  }
});

class CartComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      failed: false,
      purchased: false,
      hasFunds: true
    };

    this.handlePurchase = this.handlePurchase.bind(this);
  }

  handlePurchase() {
    CartService.executeCartService(this.props.cart)
      .then(response => {
        this.setState({ purchased: true });
        this.props.handleClearCart();

        //TODO: decrement user's balance by price of products
      })
      .catch(error => {
        if (error.response.data.message === "Insufficient funds.") {
          this.setState({ hasFunds: false });
        } else {
          this.setState({ failed: true });
        }
      });
  }

  purchaseAttempt = () => {
    if (this.state.failed) {
      return (
        <Typography className="failedMsg" align="center">
          Sorry these items are currently unavailable
        </Typography>
      );
    } else if (!this.state.hasFunds) {
      return (
        <Typography className="failedMsg" align="center">
          Sorry you have insufficient funds
        </Typography>
      );
    }
  };

  items = () => {
    const { classes } = this.props;
    if (this.props.empty) {
      if (this.state.purchased) {
        return (
          <Container className={classes.emptyMsg}>
            <Typography className="emptyMsg" variant="h4" align="center">
              Thankyou for your purchase
            </Typography>
          </Container>
        );
      } else {
        return (
          <Container className={classes.emptyMsg}>
            <Typography className="emptyMsg" variant="h4" align="center">
              You're cart is empty
            </Typography>
          </Container>
        );
      }
    } else
      return (
        <Container>
          <Typography
            className={classes.cartTitle}
            variant="h5"
            align="center"
            gutterBottom
          >
            Cart
          </Typography>
          <List className={classes.cartItems} disablePadding>
            {this.props.cart.map((product, index) => (
              <React.Fragment key={index}>
                <ListItem className={classes.cartItem}>
                  <ListItemAvatar>
                    <Avatar alt={product.name} src={product.imageUrl} />
                  </ListItemAvatar>
                  <ListItemText
                    className="cartItem"
                    key={product.id}
                    primary={product.productName}
                    secondary={product.description}
                  ></ListItemText>
                </ListItem>
                <Divider />
              </React.Fragment>
            ))}
          </List>
          {this.purchaseAttempt()}
          <Grid container justify="space-between">
            <Grid item>
              <Button
                className={classes.clearCartBut}
                type="reset"
                variant="contained"
                onClick={this.props.handleClearCart}
              >
                Clear Cart
              </Button>
            </Grid>
            <Grid item>
              <Button
                className={classes.BuyBut}
                type="submit"
                variant="contained"
                onClick={this.handlePurchase}
              >
                Purchase
              </Button>
            </Grid>
          </Grid>
        </Container>
      );
  };

  render() {
    const { classes } = this.props;
    return (
      <React.Fragment>
        <Paper className={classes.cartPaper} max-width="sm">
          {this.items()}
        </Paper>
      </React.Fragment>
    );
  }
}

export default withStyles(styles)(CartComponent);
