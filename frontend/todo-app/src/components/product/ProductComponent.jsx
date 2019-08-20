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

import * as lodash from "lodash";

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

function loadImage(index) {
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

  return images[index];
}

class ProductComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      cards: [
        {
          id: 1,
          name: "Programmer Guide",
          description: "blablablablabla"
        },
        {
          id: 2,
          name: "Elephant Book",
          description: "blablablablabla"
        },
        {
          id: 3,
          name: "Self taught programmer",
          description: "blablablablabla"
        },
        {
          id: 4,
          name: "Computer Science Book",
          description: "blablablablabla"
        },
        {
          id: 5,
          name: "Beginning Programming Reference for dummies",
          description: "blablablablabla"
        },
        {
          id: 6,
          name: "Computer Science Distilled",
          description: "blablablablabla"
        },
        {
          id: 7,
          name: "Computer science principles",
          description: "blablablablabla"
        },
        {
          id: 8,
          name: "Structure and Interpretation of Computer Programs",
          description: "blablablablabla"
        },
        {
          id: 9,
          name: "AP Computer Science A",
          description: "blablablablabla"
        }
      ]
    };
  }

  sortProduct = cards => {
    const newCards =
      cards[0].id < cards[1].id
        ? lodash.orderBy(cards, ["id"], ["desc"])
        : lodash.orderBy(cards, ["id"], ["asc"]);
    this.setState({ cards: newCards });
    return newCards;
  };

  render() {
    const { classes } = this.props;
    return (
      <React.Fragment>
        <CssBaseline />

        <main>
          <Container className={classes.cardGrid} maxWidth="md">
            <Grid container justify="flex-end">
              <Tooltip title="Sort by name">
                <IconButton
                  className={classes.button}
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
                  className="product"
                  item
                  key={card.id}
                  xs={12}
                  sm={6}
                  md={4}
                >
                  <Card className={classes.card}>
                    <CardMedia
                      className={classes.cardMedia}
                      image={loadImage(card.id)}
                      title="Image title"
                    />
                    <CardContent className={classes.cardContent}>
                      <Typography gutterBottom variant="h5" component="h2">
                        {card.name}
                      </Typography>
                      <Typography>{card.description}</Typography>
                    </CardContent>
                    <CardActions>
                      <Button
                        variant="outlined"
                        href="/product/2"
                        className={classes.button}
                      >
                        View
                      </Button>
                      <Button size="small" color="primary">
                        Edit
                      </Button>

                      <IconButton
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
        </main>
      </React.Fragment>
    );
  }
}

export default withStyles(styles)(ProductComponent);
