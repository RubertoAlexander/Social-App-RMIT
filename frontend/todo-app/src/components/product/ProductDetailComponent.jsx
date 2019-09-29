import * as React from "react";
import Card from "@material-ui/core/Card";
import CardMedia from "@material-ui/core/CardMedia";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import CardActions from "@material-ui/core/CardActions";
import Button from "@material-ui/core/Button";
import IconButton from "@material-ui/core/IconButton";
import AddShoppingCartIcon from "@material-ui/icons/AddShoppingCart";
import Grid from "@material-ui/core/Grid";
import ArrowBackIcon from "@material-ui/icons/ArrowBack";

export class ProductDetailComponent extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    const { classes } = this.props;
    return (
      <Grid container justify="center">
        <Grid item xs={1}>
          <IconButton
            onClick={() => {
              this.props.setDisplayProductDetail(false);
            }}
          >
            <ArrowBackIcon />
          </IconButton>
        </Grid>
        <Grid
          className="product"
          item
          key={this.props.card.id}
          xs={11}
          sm={11}
          md={11}
        >
          <Card className={classes.card}>
            <CardMedia
              className={classes.cardMedia}
              image={this.props.loadImage(this.props.card.id)}
              title="Image title"
            />
            <CardContent className={classes.cardContent}>
              <Typography gutterBottom variant="h5" component="h2">
                {this.props.card.name}
              </Typography>
              <Typography>{this.props.card.description}</Typography>
            </CardContent>
            <CardActions>
              <Button size="small" color="primary" className="edit-button">
                Edit
              </Button>
              <IconButton
                onClick={() => {
                  this.props.handleAddToCart(this.props.card);
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
      </Grid>
    );
  }
}
