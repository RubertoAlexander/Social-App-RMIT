import axios from "axios";
import { API_URL } from "../../Constants";
//import SESSION_ATTRIBUTE_TOKEN from "../todo/AuthenticationService";

class ProductsService {
  retrieveProducts() {
    console.log(sessionStorage.getItem("userToken"));
    return axios.get(`${API_URL}/jpa/products/all`, {
      headers: { authorization: sessionStorage.getItem("userToken") }
    });
  }
}

export default new ProductsService();
