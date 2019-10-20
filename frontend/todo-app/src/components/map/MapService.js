import axios from "axios";
import { API_URL } from "../../Constants";

class MapService {
  userID = sessionStorage.getItem("user_id");

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

  setLocation(loc) {
    let data = {
      location: loc,
      userId: this.userID
    };
    return axios.post(`${API_URL}/location/set/`, data);
  }

  getUserLocation(userID) {
    return axios.get(`${API_URL}/get/location/${userID}`);
  }

  getAllLocations() {
    return axios.get(`${API_URL}/get/location/all`);
  }
}

export default new MapService();
