import axios from "axios";
import { API_URL } from "../Constants";

class ProductService {
  async listNewProduct(product, image) {
    const json = JSON.stringify({
      productName: product.productName,
      price: product.price,
      description: product.description
    });

    const blob = new Blob([json], {
      type: "application/json"
    });

    const data = new FormData();
    data.append("document", blob);
    data.append("file", image);

    return axios.post(`${API_URL}/jpa/products/sell`, data);
  }
}

export default new ProductService();
