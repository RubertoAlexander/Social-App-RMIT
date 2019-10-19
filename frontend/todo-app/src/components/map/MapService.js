import axios from "axios";

import { API_URL } from "../../Constants";

class MapService {
  getMapFavourites() {
    const userId = sessionStorage.getItem("user_id");
    return axios.get(`${API_URL}/favourite/${userId}`);
  }
}

export default new MapService();
