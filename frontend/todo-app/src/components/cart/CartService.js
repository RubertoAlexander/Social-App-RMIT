import axios from "axios";
import { API_URL } from "../../Constants";

class CartService {
  executeCartService(cart) {
    const userID = sessionStorage.getItem("user_id");
    let itemIds = [];

    for (let i = 0; i < cart.length; i++) {
      itemIds.push(cart[i].id);
    }

    return axios.post(`${API_URL}/api/orders/${userID}`, itemIds);
  }
}

export default new CartService();
