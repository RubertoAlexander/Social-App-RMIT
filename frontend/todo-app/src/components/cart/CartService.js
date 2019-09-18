import axios from "axios";
import AuthenticationService from "../todo/AuthenticationService.js";
import { API_URL } from "../../Constants";

const USER_NAME_SESSION_ATTRIBUTE_NAME = "authenticatedUser";

class CartService {
  executeCartService(cart) {
    let username = AuthenticationService.getLoggedInUserName();
    let item;
    for (let i = 0; i < cart.length; i++) {
      //item = cart[i];
      //this.sendPurchaseItem(username, item)
      this.sendPurchaseItem(username, cart[i]);
    }
  }

  sendPurchaseItem(user, item) {
    return axios.post(`${API_URL}/orders`, {
      user,
      //item.id
      item
    });
  }
}

export default new CartService();
