import React, { Component } from "react";
import TodoApp from "./components/todo/TodoApp";
import "./App.css";
import "./bootstrap.css";
import ThemeProvider from "@material-ui/styles/ThemeProvider";
import { createMuiTheme } from "@material-ui/core";
import { BrowserRouter } from "react-router-dom";

const theme = createMuiTheme({
  palette: {
    secondary: {
      light: "#757ce8",
      main: "#3f50b5",
      dark: "#002884",
      contrastText: "#fff"
    },
    primary: {
      light: "#ff7961",
      main: "#f44336",
      dark: "#ba000d",
      contrastText: "#000"
    }
  },
  status: {
    danger: "orange"
  }
});

class App extends Component {
  render() {
    return (
      <ThemeProvider theme={theme}>
        <BrowserRouter>
          <TodoApp />
        </BrowserRouter>
      </ThemeProvider>
    );
  }
}

export default App;
