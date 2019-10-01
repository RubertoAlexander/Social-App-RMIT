import React from "react";

import Button from "@material-ui/core/Button";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import CardMedia from "@material-ui/core/CardMedia";
import CssBaseline from "@material-ui/core/CssBaseline";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import { withStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import Sort from "@material-ui/icons/Sort";
import IconButton from "@material-ui/core/IconButton";
import Tooltip from "@material-ui/core/Tooltip";
import AddShoppingCartIcon from "@material-ui/icons/AddShoppingCart";

import product1 from "../../img/product (1).jpg";
import product2 from "../../img/product (2).jpg";
import product3 from "../../img/product (3).jpg";
import product4 from "../../img/product (4).jpg";
import product5 from "../../img/product (5).jpg";
import product6 from "../../img/product (6).jpg";
import product7 from "../../img/product (7).jpg";
import product8 from "../../img/product (8).jpg";
import product9 from "../../img/product (9).jpg";
import product10 from "../../img/product (10).jpg";
import product11 from "../../img/product (11).jpg";
import product12 from "../../img/product (12).jpg";
import product13 from "../../img/product (13).jpg";
import no_image from "../../img/no-photo-available.png";

import * as lodash from "lodash";
import { ProductDetailComponent } from "./ProductDetailComponent";

const styles = theme => ({
  icon: {
    marginRight: theme.spacing(2)
  },
  heroContent: {
    backgroundColor: theme.palette.background.paper,
    padding: theme.spacing(8, 0, 6)
  },
  heroButtons: {
    marginTop: theme.spacing(4)
  },
  cardGrid: {
    paddingTop: theme.spacing(8),
    paddingBottom: theme.spacing(8)
  },
  card: {
    height: "100%",
    display: "flex",
    flexDirection: "column"
  },
  cardMedia: {
    paddingTop: "56.25%" // 16:9
  },
  cardContent: {
    flexGrow: 1
  },
  footer: {
    backgroundColor: theme.palette.background.paper,
    padding: theme.spacing(6)
  }
});

class ProductComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      cards: this.props.cards,
      viewProductDetail: {},
      displayProductDetail: false
    };

    this.handleAddToCart = this.handleAddToCart.bind(this);
  }

  handleAddToCart = card => {
    this.props.handleAddToCart(card);
  };

  sortProduct(cards) {
    if (!cards) {
      return undefined;
    }
    for (const card of cards) {
      if (!card.name) {
        return undefined;
      }
    }
    const nameOfProducts = cards.map(card => {
      return card.name;
    });
    let arraySortedDesc = false;
    for (let i = 0; i < nameOfProducts.length; i++) {
      if (i + 1 === nameOfProducts.length) {
        break;
      }
      if (nameOfProducts[i] < nameOfProducts[i + 1]) {
        continue;
      }
      arraySortedDesc = true;
    }

    const newCards = arraySortedDesc
      ? lodash.orderBy(cards, ["name"], ["asc"])
      : lodash.orderBy(cards, ["name"], ["desc"]);
    this.setState({ cards: newCards });
    return newCards;
  }

  loadImage = index => {
    const images = [
      product1,
      product2,
      product3,
      product4,
      product5,
      product6,
      product7,
      product8,
      product9,
      product10,
      product11,
      product12,
      product13
    ];

    return no_image;
  };

  setDisplayProductDetail = value => {
    this.setState({ displayProductDetail: value });
  };

  toggleViewProductDetail = card => {
    this.setState({ viewProductDetail: card, displayProductDetail: true });
  };

  render() {
    const { classes } = this.props;
    return (
      <React.Fragment>
        <CssBaseline />

        <main>
          {this.state.displayProductDetail ? (
            <Container>
              <ProductDetailComponent
                setDisplayProductDetail={this.setDisplayProductDetail}
                card={this.state.viewProductDetail}
                loadImage={this.loadImage}
                handleAddToCart={this.handleAddToCart}
                classes={classes}
              />
            </Container>
          ) : (
            <Container className={classes.cardGrid} maxWidth="md">
              <Grid container justify="flex-end">
                <Tooltip title="Sort by name">
                  <IconButton
                    className="sort-button"
                    aria-label="sort"
                    onClick={() => {
                      this.sortProduct(this.state.cards);
                    }}
                  >
                    <Sort />
                  </IconButton>
                </Tooltip>
              </Grid>
              <Grid container spacing={4}>
                {this.state.cards.map(card => (
                  <Grid
                    key={card.id}
                    className="product"
                    item
                    xs={12}
                    sm={6}
                    md={4}
                  >
                    <Card className={classes.card}>
                      <CardMedia
                        className={classes.cardMedia}
                        image={this.loadImage(card.id)}
                        title="Image title"
                      />
                      <CardContent className={classes.cardContent}>
                        <Typography gutterBottom variant="h5" component="h2">
                          {card.productName}
                        </Typography>
                        <Typography>{card.description}</Typography>
                      </CardContent>
                      <CardActions>
                        <Button
                          variant="outlined"
                          onClick={() => {
                            this.toggleViewProductDetail(card);
                          }}
                          className="view-button"
                        >
                          View
                        </Button>
                        <Button
                          size="small"
                          color="primary"
                          className="edit-button"
                        >
                          Edit
                        </Button>

                        <IconButton
                          onClick={() => {
                            this.handleAddToCart(card);
                          }}
                          color="primary"
                          className={classes.button}
                          aria-label="add to shopping cart"
                        >
                          <AddShoppingCartIcon />
                        </IconButton>
                      </CardActions>
                    </Card>
                  </Grid>
                ))}
              </Grid>
            </Container>
          )}
        </main>
      </React.Fragment>
    );
  }
}

export default withStyles(styles)(ProductComponent);
