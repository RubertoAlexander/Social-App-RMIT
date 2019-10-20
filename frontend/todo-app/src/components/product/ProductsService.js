import axios from "axios";
import { API_URL } from "../../Constants";

class ProductsService {
  /**
   * Retrieves all products from backend
   */
  retrieveProducts() {
    return axios.get(`${API_URL}/jpa/products/all`);
  }

  retrievePastOrders(userId) {
    return axios.get(`${API_URL}/user/${userId}/orders`);
  }
}

export default new ProductsService();
