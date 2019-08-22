import React from "react";
import {
  List,
  ListItem,
  ListItemText,
  ListItemAvatar,
  Avatar,
  Divider,
  Container,
  Paper,
  withStyles,
  Button
} from "@material-ui/core";
import { Typography } from "@material-ui/core";

const styles = theme => ({
  cartBox: {
    marginTop: theme.spacing(8)
  },
  title: {
    paddingTop: theme.spacing(4)
  }
});

class CartComponent extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    const { classes } = this.props;
    return (
      <React.Fragment>
        <Container maxWidth="md" className={classes.cartBox}>
          <Paper>
            <Typography variant="h3" gutterBottom className={classes.title}>
              Cart
            </Typography>

            <List>
              {this.props.cart.map(product => (
                <React.Fragment>
                  <ListItem>
                    <ListItemAvatar>
                      <Avatar alt={product.name} src={product.imageUrl} />
                    </ListItemAvatar>

                    <ListItemText
                      primary={product.name}
                      secondary={product.description}
                    ></ListItemText>
                  </ListItem>
                  <Divider />
                </React.Fragment>
              ))}
            </List>
            <Button variant="contained" color="primary">
              Complete Order
            </Button>
          </Paper>
        </Container>
      </React.Fragment>
    );
  }
}

export default withStyles(styles)(CartComponent);
