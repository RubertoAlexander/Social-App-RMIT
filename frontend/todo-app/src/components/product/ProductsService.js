import axios from "axios";
import { API_URL } from "../../Constants";

//import SESSION_ATTRIBUTE_TOKEN from "../todo/AuthenticationService";

class ProductsService {
  retrieveProducts() {
    return axios.get(`${API_URL}/jpa/products/all`);
  }

  retrievePastOrders(userId) {
    return axios.get(`${API_URL}/user/${userId}/orders`);
  }
}

export default new ProductsService();
