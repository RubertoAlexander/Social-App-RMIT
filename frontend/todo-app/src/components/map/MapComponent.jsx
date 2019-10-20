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

import locationImage from "../../img/locationImg.png";
import yourLocationImage from "../../img/yourLocationImg.png";

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
  },
  showUsers: {
    marginTop: "20%",
    marginLeft: "10%"
  }
});

export class MapComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hasLocation: false,
      hasClasses: true,
      showClasses: false,
      favourites: [], //stores all user favourites in this array
      markerDesc: "Melbourne", //stores the description of the marker on the map
      markerPosition: {
        lat: -37.808,
        lng: 144.9645
      }, //stores the marker's longitude and latitude
      loading: true,

      showAllUsers: false
    };
    this.retrieveClasses();

    let curLat, curLong;
    this.setLocation();

    this.getAllLocations();
    this.handleShowClasses = this.handleShowClasses.bind(this);
    this.handleShowAllUsers = this.handleShowAllUsers.bind(this);
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

  /**
   * Toggles the state of showClasses when button pressed
   */
  handleShowClasses() {
    this.setState({ showClasses: !this.state.showClasses });
  }

  /**
   * Toggles the state of showAllUsers when button pressed
   */
  handleShowAllUsers() {
    this.setState({ showAllUsers: !this.state.showAllUsers });
  }

  /**
   * Retrieves all classes for the current user
   */
  async retrieveClasses() {
    await MapService.retrieveClasses();

    //Set the state of hasClasses to false if there are none
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

  /**
   * Set the location of the current user using browser geolocation
   */
  async setLocation() {
    await navigator.geolocation.getCurrentPosition(position => {
      MapService.setLocation(
        position.coords.latitude + "," + position.coords.longitude
      )
        .then(() => {
          this.state.hasLocation = true;
          this.curLat = position.coords.latitude;
          this.curLong = position.coords.longitude;
        })
        .catch(() => {});
    });
  }

  /**
   * Get the location of any user using their ID
   * @param {int} userID ID of the user
   */
  async getLocation(userID) {
    let location;
    MapService.getUserLocation(userID).then(response => {
      location = response.data;
    });
  }

  /**
   * Get the location of all users and set in storage
   */
  async getAllLocations() {
    await MapService.getAllLocations().then(response => {
      sessionStorage.setItem("userLocs", JSON.stringify(response.data));
    });
  }

  /**
   * Renders user's classes on the map
   */
  renderClasses = () => {
    if (this.state.showClasses) {
      if (this.state.hasClasses) {
        const buildings = this.getClasses();
        if (!buildings) {
          return "";
        }
        let clusters = this.groupBy(buildings, "group");

        //If the classes are in the same building place together
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

  /**
   * Show the location of current user on the map with a blue icon
   */
  renderYourLocation = () => {
    if (this.state.hasLocation) {
      return (
        <Marker
          position={{
            lat: this.curLat,
            lng: this.curLong
          }}
          title="Your Location"
          icon={yourLocationImage}
        />
      );
    }
  };

  /**
   * Show the location of all users on the map with a black icon
   */
  renderAllLocations = () => {
    if (this.state.showAllUsers) {
      let allLocations = JSON.parse(sessionStorage.getItem("userLocs"));
      allLocations = this.cleanLocations(allLocations);

      return allLocations.map((user, i) => (
        <Marker
          key={i}
          position={{
            lat: this.getLatFromString(user.location),
            lng: this.getLongFromString(user.location)
          }}
          title={"" + user.userId}
          icon={locationImage}
        />
      ));
    }
  };

  /**
   * Removes locations with null values
   * @param {Array} locations array holding location details of all users
   */
  cleanLocations(locations) {
    for (let i = 0; i < locations.length; i++) {
      if (isNull(locations[i].location)) {
        locations.splice(i, 1);
      }
    }
    return locations;
  }

  /**
   * Retrieve the latitude number value from a coordinates string
   * @param {String} locationString coordinates of location with comma delimeter
   */
  getLatFromString(locationString) {
    let locationArr = locationString.split(",");
    let latString = locationArr[0];
    return parseFloat(latString);
  }

  /**
   * Retrieve the longitude number value from a coordinates string
   * @param {String} locationString  coordinates of location with commma delimeter
   */
  getLongFromString(locationString) {
    let locationArr = locationString.split(",");
    let longString = locationArr[1];
    return parseFloat(longString);
  }

  /**
   * Groups elements in an array by property
   * @param {Array} arr array to group values in
   * @param {String} prop property to group values by, coordinates
   */
  groupBy(arr, prop) {
    if (!arr || !prop) {
      return undefined;
    }
    const map = new Map(Array.from(arr, obj => [obj[prop], []]));
    arr.forEach(obj => map.get(obj[prop]).push(obj));
    return Array.from(map.values());
  }

  /**
   * Get all the names of the classes in the same location
   * @param {Array} cluster classes that are in the same location
   */
  getClusteredString(cluster) {
    let classesHere = "";
    for (let i = 0; i < cluster.length; i++) {
      classesHere += "\n" + cluster[i].classString;
    }
    return classesHere;
  }

  /**
   * Retrieve the latitude location of a building number
   * @param {String} building building number of RMIT
   */
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

  /**
   * Retrieve the longitude location of a building number
   * @param {String} building building number from RMIT
   */
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

  /**
   * Construct an array of buildings for all classes
   */
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

  /**
   * Retrieve the building number from RMIT class location
   * @param {String} classString full class location (80.02.007)
   */
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
                  <ToggleButton
                    className={classes.showUsers}
                    value="users"
                    size="small"
                    selected={this.state.showAllUsers}
                    onChange={this.handleShowAllUsers}
                  >
                    Show Users
                  </ToggleButton>
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
                      {this.renderYourLocation()}
                      {this.renderAllLocations()}
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
