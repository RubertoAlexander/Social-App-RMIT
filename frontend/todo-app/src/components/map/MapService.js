import axios from "axios";
import { API_URL } from "../../Constants";

class MapService {
  userID = sessionStorage.getItem("user_id");

  /**
   * Gets all classes that the current user has and sets it in session storage
   */
  retrieveClasses() {
    return axios
      .get(`${API_URL}/find/classes/${this.userID}`)
      .then(response => {
        sessionStorage.setItem("classes", JSON.stringify(response.data));
      })
      .catch(error => {
        console.log("ERROR: no classes");
        sessionStorage.setItem("classes", "");
      });
  }

  /**
   * Sets the location in database for the current user
   * @param {String} loc coordinates of the user separated by a comma
   */
  setLocation(loc) {
    let dateNow = new Date().getTime();
    let data = {
      location: loc,
      userId: this.userID,
      date: dateNow
    };
    return axios.post(`${API_URL}/location/set/`, data);
  }

  /**
   * Retrieves the coordinats of the user specified
   * @param {int} userID
   */
  getUserLocation(userID) {
    return axios.get(`${API_URL}/get/location/${userID}`);
  }

  /**
   * Retrieves the location of all users in the database
   */
  getAllLocations() {
    return axios.get(`${API_URL}/get/location/all`);
  }
}

export default new MapService();
