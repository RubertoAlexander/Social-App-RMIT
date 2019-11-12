import React from "react";
import CartComponent from "./CartComponent";
import { shallow } from "enzyme";

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
    console.log(wrapper.html());
    const cartItem = wrapper.dive().find(".cartItem");
    const cartProp = wrapper.dive().instance().props.cart;

    expect(cartItem.length).toEqual(cart.length);
    expect(cartProp.length).toEqual(1);
  });

  it("Displays an empty cart message", () => {
    const cart = [];

    wrapper = shallow(<CartComponent cart={cart} empty={true} />);
    const errorMessage = wrapper.dive().find(".emptyMsg");
    const cartProp = wrapper.dive().instance().props.cart;

    expect(cartProp.length).toEqual(0);
    expect(errorMessage.length).toEqual(1);
  });

  it("Has a clear cart button", () => {
    const cart = [
      {
        id: 1,
        name: "Programmer Guide",
        description: "blablablablabla"
      }
    ];

    wrapper = shallow(<CartComponent cart={cart} />);
    const clearBut = wrapper.dive().find("#clearBut");

    expect(clearBut.length).toEqual(1);
  });

  it("Has a buy button", () => {
    const cart = [
      {
        id: 1,
        name: "Programmer Guide",
        description: "blablablablabla"
      }
    ];

    wrapper = shallow(<CartComponent cart={cart} />);
    const buyBut = wrapper.dive().find("#buyBut");

    expect(buyBut.length).toEqual(1);
  });

  /*it("Failed purchase displays", () => {
    const cart = [
      {
        id: 1,
        name: "Programmer Guide",
        description: "blablablablabla"
      }
    ];
    wrapper = shallow(<CartComponent cart={cart}/>);
    wrapper.dive().instance().setFailedState();
    expect(wrapper.find(".failed").length).toEqual(1);
  });*/

  //Not working, but runs
  /*it("Clears the cart from all items", () => {
    const testCart = [
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

    wrapper = shallow(<TodoApp />);
    wrapper.instance.state = {cart: testCart, cartEmpty: false};
    wrapper.instance.handleClearCart();
    
    expect(wrapper.state("cartEmpty")).toEqual(false);
  });*/
});
