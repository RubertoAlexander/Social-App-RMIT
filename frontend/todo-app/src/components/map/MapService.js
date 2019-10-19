import axios from "axios";
import { API_URL } from "../../Constants";

class MapService {
  retrieveClasses() {
    const userId = sessionStorage.getItem("user_id");
    return axios
      .get(`${API_URL}/find/classes/${userId}`)
      .then(response => {
        sessionStorage.setItem("classes", JSON.stringify(response.data));
      })
      .catch(error => {
        console.log("ERROR: no classes");
        sessionStorage.setItem("classes", "");
      });
  }

  getMapFavourites() {
    const userId = sessionStorage.getItem("user_id");
    return axios.get(`${API_URL}/favourite/${userId}`);
  }
}

export default new MapService();
