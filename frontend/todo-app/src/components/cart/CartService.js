import axios from "axios";
import AuthenticationService from "../todo/AuthenticationService.js";
import { API_URL } from "../../Constants";

class CartService {
  /**
   * Sends items in cart to backend
   * @param {Array} cart all items currently in cart
   */
  executeCartService(cart) {
    let userID = sessionStorage.getItem("user_id");
    let itemIds = [];

    for (let i = 0; i < cart.length; i++) {
      itemIds.push(cart[i].id);
    }

    return axios.post(`${API_URL}/api/orders/${userID}`, itemIds);
  }
}

export default new CartService();
