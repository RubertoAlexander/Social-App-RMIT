import React from "react";
import { GoogleApiWrapper, Map, Marker } from "google-maps-react";

import "./Map.css";
import Grid from "@material-ui/core/Grid";
import { Typography } from "@material-ui/core";
import MapService from "./MapService";
import Card from "@material-ui/core/Card";
import CircularProgress from "@material-ui/core/CircularProgress";

export class MapComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      favourites: [], //stores all user favourites in this array
      markerDesc: "Melbourne", //stores the description of the marker on the map
      markerPosition: {
        lat: -37.808,
        lng: 144.9645
      }, //stores the marker's longitude and latitude
      loading: true
    };
  }

  componentDidMount() {
    //get all favourites from the backend for this user
    MapService.getMapFavourites().then(response => {
      const favourites = response.data;

      this.setState({ loading: false, favourites: favourites });
    });
  }

  //this function create a marker on the map with the specified longitude and latitude
  createMarkerOnMap = favourite => {
    const extractedLat = favourite.location.split(",")[0];
    const extractedLong = favourite.location.split(",")[1].slice(1);
    console.log(extractedLat);
    console.log(extractedLong);

    this.setState({
      markerDesc: favourite.description,
      markerPosition: {
        lat: extractedLat,
        long: extractedLong
      }
    });
  };

  render() {
    const mapStyles = {
      width: "100%",
      height: "100%",
      position: "relative"
    };

    return (
      <Grid container id="map-component">
        <Grid item xs={2} className="align-text-center">
          <Typography gutterBottom variant="h5" component="h2">
            Favourites
          </Typography>
          <div>{this.state.loading ? <CircularProgress /> : ""}</div>
          <Grid container>
            {/*create a new card for every favourite we have for that user*/}
            {this.state.favourites.map((favourite, key) => {
              return (
                <React.Fragment key={key}>
                  <Grid item xs={12} sm={6}>
                    <Card
                      onClick={() => {
                        this.createMarkerOnMap(favourite);
                      }}
                    >
                      <div>Desc: {favourite.description}</div>
                    </Card>
                  </Grid>
                </React.Fragment>
              );
            })}
          </Grid>
        </Grid>
        <Grid item xs={10} id="map">
          <Map
            google={this.props.google}
            zoom={16}
            style={mapStyles}
            initialCenter={{ lat: -37.808, lng: 144.9645 }}
          >
            {this.state.desc !== "" ? (
              <Marker
                title={this.state.markerDesc}
                name={this.state.markerDesc}
                position={this.state.markerPosition}
              />
            ) : (
              ""
            )}
          </Map>
        </Grid>
      </Grid>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: "AIzaSyALA3BO0lJTnnbZNbHVTd4TwSjSNoqo85Q"
})(MapComponent);
