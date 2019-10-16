import React from "react";
import { GoogleMap, LoadScript, Marker } from "@react-google-maps/api";

import "./Map.css";
import Grid from "@material-ui/core/Grid";
import {
  Typography,
  withStyles,
  Container,
  Paper,
  CssBaseline
} from "@material-ui/core";
import ToggleButton from "@material-ui/lab/ToggleButton";

import MapService from "../map/MapService";
import { isNull } from "util";

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
    padding: "1%"
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
      showClasses: false
    };
    this.retrieveClasses();
    this.handleShowClasses = this.handleShowClasses.bind(this);
  }

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

  renderClasses = () => {
    if (this.state.showClasses) {
      if (this.state.hasClasses) {
        const buildings = this.getClasses();
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

    if (this.state.hasClasses) {
      const classes = JSON.parse(sessionStorage.getItem("classes"));
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
                </Grid>

                <Grid item xs={8} className={classes.map}>
                  <LoadScript
                    id="script-loader"
                    googleMapsApiKey="AIzaSyALA3BO0lJTnnbZNbHVTd4TwSjSNoqo85Q"
                  >
                    <GoogleMap
                      id="google-map"
                      mapContainerStyle={{
                        height: "100%",
                        width: "100%"
                      }}
                      zoom={17}
                      center={{
                        lat: -37.808,
                        lng: 144.964
                      }}
                    >
                      {this.renderClasses()}
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
