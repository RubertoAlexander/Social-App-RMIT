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
  }

  render() {
    return (
      <React.Fragment>
        <Paper max-width="sm">
          <Container>
            <Typography variant="h5" gutterBottom>
              Cart
            </Typography>
            <List disablePadding>
              {this.props.cart.map((product, index) => (
                <React.Fragment key={index}>
                  <ListItem>
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
              type="reset"
              variant="contained"
              onClick={this.props.handleClearCart}
            >
              Clear Cart
            </Button>
          </Container>
        </Paper>
      </React.Fragment>
    );
  }
}
