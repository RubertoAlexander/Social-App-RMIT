import React from "react";
import {
  List,
  ListItem,
  ListItemText,
  ListItemAvatar,
  Avatar,
  Divider,
  Container,
  Paper
} from "@material-ui/core";
import { Typography } from "@material-ui/core";

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
              {this.props.cart.map(product => (
                <React.Fragment>
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
          </Container>
        </Paper>
      </React.Fragment>
    );
  }
}
