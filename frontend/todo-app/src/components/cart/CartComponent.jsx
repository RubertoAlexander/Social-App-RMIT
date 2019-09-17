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
  withStyles
} from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";

const styles = theme => ({
  cartPaper: {
    display: "block",
    margin: "5% 20% 5% 20%"
    //paddingBottom: "20%"
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
    //marginLeft: "25%",
    justifySelf: "center"
  },
  clearCartBut: {
    marginBottom: "5%"
  }
});

class CartComponent extends React.Component {
  constructor(props) {
    super(props);

    this.state = { empty: this.props.empty };
  }

  handleClearCart = () => {
    this.setState({ empty: true });
    this.props.handleClearCart();
  };

  items = () => {
    const { classes } = this.props;
    if (this.state.empty) {
      return (
        <Container className={classes.emptyMsg}>
          <Typography className="emptyMsg" variant="h3" align="center">
            You're cart is empty
          </Typography>
        </Container>
      );
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
                    primary={product.name}
                    secondary={product.description}
                  ></ListItemText>
                </ListItem>
                <Divider />
              </React.Fragment>
            ))}
          </List>
          <Button
            className={classes.clearCartBut}
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
