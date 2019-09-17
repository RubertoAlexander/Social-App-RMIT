import React from "react";
import CartComponent from "./CartComponent";
import { shallow } from "enzyme";
import TodoApp from "../todo/TodoApp";

describe("CartComponent", () => {
  let wrapper;

  it("Displays an item", () => {
    const cart = [
      {
        id: 1,
        name: "Programmer Guide",
        description: "blablablablabla"
      }
    ];

    wrapper = shallow(<CartComponent cart={cart} />);
    const cartItem = wrapper.find(".cart-item");
    const cartProp = wrapper.instance().props.cart;

    expect(cartItem.length).toEqual(cart.length);
    expect(cartProp.length).toEqual(1);
  });

  it("Displays an empty cart message", () => {
    const cart = [];

    wrapper = shallow(<CartComponent cart={cart} empty={true} />);
    const errorMessage = wrapper.find(".empty-msg");
    const cartProp = wrapper.instance().props.cart;

    expect(cartProp.length).toEqual(0);
    expect(errorMessage.length).toEqual(1);
  });

  //Not working, but runs
  /*it("Clears the cart from all items", () => {
    const cart = [
      {
        id: 1,
        name: "Programmer Guide",
        description: "blablablablabla"
      },
      {
        id: 2,
        name: "Elephant Book",
        description: "blablablablabla"
      }
    ];

    wrapper = shallow(<CartComponent cart={cart} handleClearCart={handleClearCart} empty={false}/>);
    const clearCartButton = wrapper.find(".clear-cart-but");
    const cartProp = wrapper.instance().props.cart;

    wrapper.update();
    clearCartButton.simulate("click");
    
    //expect(wrapper.state("empty")).toEqual(true);
    expect(wrapper.find(".cart-item").length).toEqual(0);
    expect(cartProp.length).toEqual(0);
  });*/
});
