import axios from "axios";
import { API_URL } from "../../Constants";

class ProductsService {
  retrieveProducts() {
    return axios.get(`${API_URL}/jpa/products/all`);
  }
}

export default new ProductsService();
