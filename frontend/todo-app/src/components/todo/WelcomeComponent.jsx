import React, { Component } from "react";
import { Link } from "react-router-dom";
import HelloWorldService from "../../api/todo/HelloWorldService.js";
import Grid from "@material-ui/core/Grid";

class WelcomeComponent extends Component {
  constructor(props) {
    super(props);
    this.retrieveWelcomeMessage = this.retrieveWelcomeMessage.bind(this);
    this.state = {
      welcomeMessage: ""
    };
    this.handleSuccessfulResponse = this.handleSuccessfulResponse.bind(this);
    this.handleError = this.handleError.bind(this);
  }

  render() {
    return (
      <React.Fragment>
        <Grid container>
          <Grid item xs={12}>
            <h1>Welcome!</h1>
          </Grid>
          <Grid item xs={12}>
            <div>
              Welcome {this.props.match.params.name}. You can manage your todos{" "}
              <Link to="/todos">here</Link>.
            </div>
          </Grid>
          <Grid item xs={12}>
            Click here to get a customized welcome message.
            <button
              onClick={this.retrieveWelcomeMessage}
              className="btn btn-success"
            >
              Get Welcome Message
            </button>
          </Grid>
          <Grid item xs={12}>
            {this.state.welcomeMessage}
          </Grid>
        </Grid>
      </React.Fragment>
    );
  }

  retrieveWelcomeMessage() {
    // HelloWorldService.executeHelloWorldService()
    // .then( response => this.handleSuccessfulResponse(response) )

    // HelloWorldService.executeHelloWorldBeanService()
    // .then( response => this.handleSuccessfulResponse(response) )

    HelloWorldService.executeHelloWorldPathVariableService(
      this.props.match.params.name
    )
      .then(response => this.handleSuccessfulResponse(response))
      .catch(error => this.handleError(error));
  }

  handleSuccessfulResponse(response) {
    this.setState({ welcomeMessage: response.data.message });
  }

  handleError(error) {
    let errorMessage = "";

    if (error.message) errorMessage += error.message;

    if (error.response && error.response.data) {
      errorMessage += error.response.data.message;
    }

    this.setState({ welcomeMessage: errorMessage });
  }
}

export default WelcomeComponent;
