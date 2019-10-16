import axios from "axios";
import { API_URL } from "../../Constants";

class MapService {
  retrieveClasses() {
    //let userID = sessionStorage.getItem("user_id");
    let userID = "2655";
    return axios
      .get(`${API_URL}/find/classes/${userID}`)
      .then(response => {
        sessionStorage.setItem("classes", JSON.stringify(response.data));
      })
      .catch(error => {
        console.log("ERROR: no classes");
        sessionStorage.setItem("classes", []);
      });
  }
}

export default new MapService();
