import React from "react";
import { Map, GoogleApiWrapper } from "google-maps-react";

import "./Map.css";

export class MapComponent extends React.Component {
  render() {
    return (
      <div id="map-component">
        <h1>RMIT Map</h1>
        <div id="map">
          <Map
            google={this.props.google}
            zoom={16}
            //style={mapStyles}
            initialCenter={{ lat: -37.808, lng: 144.9645 }}
          />
        </div>
      </div>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: "AIzaSyALA3BO0lJTnnbZNbHVTd4TwSjSNoqo85Q"
})(MapComponent);
