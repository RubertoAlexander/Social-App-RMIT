import React from "react";
import { GoogleApiWrapper, Map } from "google-maps-react";

import "./Map.css";
import Grid from "@material-ui/core/Grid";

export class MapComponent extends React.Component {
  render() {
    return (
      <Grid container id="map-component">
        <Grid xs={2}>
          <h1>RMIT Map</h1>
        </Grid>
        <Grid item xs={10} id="map">
          <Map
            google={this.props.google}
            zoom={16}
            //style={mapStyles}
            initialCenter={{ lat: -37.808, lng: 144.9645 }}
          />
        </Grid>
      </Grid>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: "AIzaSyALA3BO0lJTnnbZNbHVTd4TwSjSNoqo85Q"
})(MapComponent);
