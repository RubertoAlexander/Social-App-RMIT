import React, { Component } from "react";
import { Typography } from "@material-ui/core";
import Grid from "@material-ui/core/Grid";

class FooterComponent extends Component {
  render() {
    return (
      <footer>
        <Grid container justify="center" alignItems="center">
          <Grid item xs={12}>
            <Typography variant="subtitle2">
              All Rights Reserved 2019 @RMIT SEPT - JavaBinks
            </Typography>
          </Grid>
        </Grid>
      </footer>
    );
  }
}

export default FooterComponent;
