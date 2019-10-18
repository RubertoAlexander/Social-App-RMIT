import React, { Component } from "react";
import AuthenticationService from "../todo/AuthenticationService.js";
import Grid from "@material-ui/core/Grid";

class LoginComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      username: "",
      password: "",
      hasLoginFailed: false,
      showSuccessMessage: false
    };
  }

  handleChange = event => {
    this.setState({
      [event.target.name]: event.target.value
    });
  };

  handleUsernameChange = event => {
    this.setState({
      [event.target.name]: event.target.value
    });
  };

  handlePasswordChange = event => {
    this.setState({ password: event.target.value });
  };

  loginClicked = () => {
    AuthenticationService.executeJwtAuthenticationService(
      this.state.username,
      this.state.password
    )
      .then(response => {
        AuthenticationService.registerSuccessfulLoginForJwt(
          this.state.username,
          response.data.token
        );
        this.props.history.push(`/`);
      })
      .catch(() => {
        this.setState({ showSuccessMessage: false });
        this.setState({ hasLoginFailed: true });
      });
  };

  render() {
    return (
      <Grid container alignItems="center" justify="center">
        <Grid style={{ textAlign: "center" }} item xs={12}>
          <h1>Login</h1>
        </Grid>
        <Grid style={{ textAlign: "center" }} item xs={12}>
          {/*<ShowInvalidCredentials hasLoginFailed={this.state.hasLoginFailed}/>*/}
          {this.state.hasLoginFailed && (
            <div className="alert alert-warning">
              Invalid Credentials or something is wrong
            </div>
          )}
          {this.state.showSuccessMessage && <div>Login Successful</div>}
          {/*<ShowLoginSuccessMessage showSuccessMessage={this.state.showSuccessMessage}/>*/}
          User Name:{" "}
          <input
            type="text"
            name="username"
            value={this.state.username}
            onChange={this.handleChange}
          />
          Password:{" "}
          <input
            type="password"
            name="password"
            value={this.state.password}
            onChange={this.handleChange}
          />
          <button className="btn btn-success" onClick={this.loginClicked}>
            Login
          </button>
        </Grid>
      </Grid>
    );
  }
}

export default LoginComponent;
