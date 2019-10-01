import axios from "axios";
import { API_URL } from "../../Constants";

export const USER_NAME_SESSION_ATTRIBUTE_NAME = "authenticatedUser";
export const SESSION_ATTRIBUTE_TOKEN = "userToken";

class AuthenticationService {
  executeBasicAuthenticationService(username, password) {
    return axios.get(`${API_URL}/basicauth`, {
      headers: { authorization: this.createBasicAuthToken(username, password) }
    });
  }

  executeJwtAuthenticationService(username, password) {
    return axios.post(`${API_URL}/authenticate`, {
      username,
      password
    });
  }

  async executeSignUpNewUser(username, password, firstName, lastName) {
    return axios.post(`${API_URL}/sign-up`, {
      username,
      password
    });
  }

  createBasicAuthToken(username, password) {
    return "Basic " + window.btoa(username + ":" + password);
  }

  registerSuccessfulLogin(username, password) {
    //let basicAuthHeader = 'Basic ' +  window.btoa(username + ":" + password)
    //console.log('registerSuccessfulLogin')
    sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username);
    this.setupAxiosInterceptors(this.createBasicAuthToken(username, password));
  }

  registerSuccessfulLoginForJwt(username, token) {
    this.setupAxiosInterceptors(this.createJWTToken(token));
    sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username);
    this.retrieveUserId(username);
  }

  retrieveUserId(username) {
    return axios.get(`${API_URL}/user/id/${username}`).then(response => {
      sessionStorage.setItem("user_id", response.data);
    });
  }

  createJWTToken(token) {
    sessionStorage.setItem(SESSION_ATTRIBUTE_TOKEN, "Bearer " + token);
    return "Bearer " + token;
  }

  logout() {
    sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    if (user === null) return false;
    return true;
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    if (user === null) return "";
    return user;
  }

  setupAxiosInterceptors(token) {
    axios.interceptors.request.use(config => {
      if (this.isUserLoggedIn()) {
        config.headers.Authorization = token;
        console.log(config.headers.Authorization);
      }
      return config;
    });
  }
}

export default new AuthenticationService();
