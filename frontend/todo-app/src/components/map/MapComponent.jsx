import React from "react";
import { GoogleMap, LoadScript, Marker } from "@react-google-maps/api";

import "./Map.css";
import Grid from "@material-ui/core/Grid";
import {
  Container,
  CssBaseline,
  Paper,
  Typography,
  withStyles
} from "@material-ui/core";
import ToggleButton from "@material-ui/lab/ToggleButton";

import MapService from "../map/MapService";
import { isNull } from "util";
import CircularProgress from "@material-ui/core/CircularProgress";
import Card from "@material-ui/core/Card";

const styles = theme => ({
  mapCont: {
    padding: "1%",
    height: "90vh"
  },
  mapTitle: {
    paddingTop: "10%",
    marginLeft: "10%"
  },
  map: {
    height: "89vh",
    width: "100%",
    marginLeft: "10%"
  },
  toolbar: {
    padding: "1%",
    textAlign: "center"
  },
  showClasses: {
    marginTop: "20%",
    marginLeft: "10%"
  }
});

const tempClasses = [
  {
    class_name: "Operating Systems Principles",
    Description: "Learn all about operating systems",
    location: "014.06.019"
  },
  {
    class_name: "Professional Computing Practice",
    Description: "Be professional in the workplace",
    location: "080.02.007"
  },
  {
    class_name: "Software Engineering: Process and Tools",
    Description: "The process of software engineering",
    location: "056.06.088"
  },
  {
    class_name: "Algorithms and Analysis",
    Description: "Algorithms of Computer Science",
    location: "014.06.016"
  }
];

export class MapComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hasClasses: true,
      showClasses: false,
      favourites: [], //stores all user favourites in this array
      markerDesc: "Melbourne", //stores the description of the marker on the map
      markerPosition: {
        lat: -37.808,
        lng: 144.9645
      }, //stores the marker's longitude and latitude
      loading: true
    };
    this.retrieveClasses();
    this.handleShowClasses = this.handleShowClasses.bind(this);
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
    const extractedLat = parseFloat(favourite.location.split(",")[0]);
    const extractedLong = parseFloat(favourite.location.split(",")[1].slice(1));

    this.setState({
      markerDesc: favourite.favourite,
      markerPosition: {
        lat: extractedLat,
        long: extractedLong
      }
    });
  };

  handleShowClasses() {
    this.setState({ showClasses: !this.state.showClasses });
  }

  async retrieveClasses() {
    await MapService.retrieveClasses();
    if (
      sessionStorage.getItem("classes") == "" ||
      isNull(sessionStorage.getItem("classes"))
    ) {
      this.state.hasClasses = false;
    }
  }

  renderFavourites = () => {
    if (this.state.favourites.length !== 0) {
      return this.state.favourites.map((favourite, index) => {
        return (
          <Marker
            key={index + 10}
            position={{
              lat: parseFloat(favourite.location.split(",")[0]),
              lng: parseFloat(favourite.location.split(",")[1].slice(1))
            }}
            title={favourite.favourite}
          />
        );
      });
    }
  };

  renderClasses = () => {
    if (this.state.showClasses) {
      if (this.state.hasClasses) {
        const buildings = this.getClasses();
        if (!buildings) {
          return "";
        }
        let clusters = this.groupBy(buildings, "group");

        return clusters.map((building, i) => (
          <Marker
            key={i}
            position={{
              lat: building[0].lat,
              lng: building[0].long
            }}
            title={this.getClusteredString(building)}
          />
        ));
      }
    }
  };

  groupBy(arr, prop) {
    if (!arr || !prop) {
      return undefined;
    }
    const map = new Map(Array.from(arr, obj => [obj[prop], []]));
    arr.forEach(obj => map.get(obj[prop]).push(obj));
    return Array.from(map.values());
  }

  getClusteredString(cluster) {
    let classesHere = "";
    for (let i = 0; i < cluster.length; i++) {
      classesHere += "\n" + cluster[i].classString;
    }
    return classesHere;
  }

  getBuildingLat(building) {
    if (building == "14") {
      return -37.8075936;
    } else if (building == "12") {
      return -37.8078165;
    } else if (building == "16") {
      return -37.8086285;
    } else if (building == "80") {
      return -37.808272;
    } else if (building == "56") {
      return -37.8051133;
    }
  }

  getBuildingLong(building) {
    if (building == "14") {
      return 144.9632783;
    } else if (building == "12") {
      return 144.9612309;
    } else if (building == "16") {
      return 144.9625144;
    } else if (building == "80") {
      return 144.9604523;
    } else if (building == "56") {
      return 144.963449;
    }
  }

  getClasses() {
    let buildings = [];

    const classesSessionStorage = sessionStorage.getItem("classes");
    if (this.state.hasClasses && classesSessionStorage !== "") {
      const classes = JSON.parse(classesSessionStorage);
      for (let i = 0; i < classes.length; i++) {
        buildings.push({
          id: i,
          classString: classes[i].className + ": " + classes[i].location,
          lat: this.getBuildingLat(this.getBuildingNumber(classes[i].location)),
          long: this.getBuildingLong(
            this.getBuildingNumber(classes[i].location)
          ),
          group:
            this.getBuildingLat(this.getBuildingNumber(classes[i].location)) +
            this.getBuildingLong(this.getBuildingNumber(classes[i].location))
        });
      }
      return buildings;
    }
  }

  getBuildingNumber(classString) {
    let building = classString.substring(0, 2);
    return building;
  }

  render() {
    const { classes } = this.props;
    const mapStyles = {
      width: "100%",
      height: "100%",
      position: "relative"
    };

    return (
      <React.Fragment>
        <CssBaseline />
        <main id="map">
          <Container maxWidth="lg">
            <Paper>
              <Grid container justify="center">
                <Grid item xs={2} className={classes.toolbar}>
                  <Typography
                    variant="h4"
                    align="center"
                    className={classes.mapTitle}
                  >
                    RMIT Map
                  </Typography>
                  <ToggleButton
                    className={classes.showClasses}
                    value="classes"
                    size="small"
                    selected={this.state.showClasses}
                    onChange={this.handleShowClasses}
                  >
                    Show Classes
                  </ToggleButton>

                  <Typography gutterBottom variant="h5" component="h2">
                    Favourites
                  </Typography>
                  <div>{this.state.loading ? <CircularProgress /> : ""}</div>
                  <Grid container>
                    {/*create a new card for every favourite we have for that user*/}
                    {this.state.favourites.map((favourite, key) => {
                      return (
                        <React.Fragment key={key}>
                          <Grid item xs={12}>
                            <Card
                              onClick={() => {
                                this.createMarkerOnMap(favourite);
                              }}
                            >
                              <div>Title: {favourite.favourite}</div>
                              <div>Description: {favourite.description}</div>
                            </Card>
                          </Grid>
                        </React.Fragment>
                      );
                    })}
                  </Grid>
                </Grid>

                <Grid item xs={8} className={classes.map}>
                  <LoadScript
                    id="script-loader"
                    googleMapsApiKey="AIzaSyALA3BO0lJTnnbZNbHVTd4TwSjSNoqo85Q"
                  >
                    <GoogleMap
                      id="google-map"
                      mapContainerStyle={mapStyles}
                      zoom={17}
                      center={{
                        lat: -37.808,
                        lng: 144.964
                      }}
                    >
                      {this.renderClasses()}
                      {this.renderFavourites()}
                    </GoogleMap>
                  </LoadScript>
                </Grid>
              </Grid>
            </Paper>
          </Container>
        </main>
      </React.Fragment>
    );
  }
}

export default withStyles(styles)(MapComponent);
