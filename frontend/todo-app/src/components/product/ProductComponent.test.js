import React from "react";
import ProductComponent from "./ProductComponent";
import { shallow } from "enzyme";

describe("ProductComponent", () => {
  const mockCards = [
    {
      id: 1,
      name: "Programmer Guide",
      description: "blablablablabla"
    },
    {
      id: 2,
      name: "Elephant Book",
      description: "blablablablabla"
    },
    {
      id: 3,
      name: "Self taught programmer",
      description: "blablablablabla"
    },
    {
      id: 4,
      name: "Computer Science Book",
      description: "blablablablabla"
    },
    {
      id: 5,
      name: "Beginning Programming Reference for dummies",
      description: "blablablablabla"
    },
    {
      id: 6,
      name: "Computer Science Distilled",
      description: "blablablablabla"
    },
    {
      id: 7,
      name: "Computer science principles",
      description: "blablablablabla"
    },
    {
      id: 8,
      name: "Structure and Interpretation of Computer Programs",
      description: "blablablablabla"
    },
    {
      id: 9,
      name: "AP Computer Science A",
      description: "blablablablabla"
    }
  ];

  it("renders without crashing", () => {
    shallow(<ProductComponent />);
  });

  it("sorts products ascending", () => {
    const wrapper = shallow(<ProductComponent />);
    const newCards = wrapper
      .dive()
      .instance()
      .sortProduct(mockCards);
    expect(newCards[0].id).toEqual(9);
    expect(newCards[0].name).toEqual("AP Computer Science A");
  });

  it("sorts products descending", () => {
    const wrapper = shallow(<ProductComponent />);
    //first sorts the products ascending
    const newCards = wrapper
      .dive()
      .instance()
      .sortProduct(mockCards);
    //another call will sort the products descending
    const newCardsDesc = wrapper
      .dive()
      .instance()
      .sortProduct(newCards);
    expect(newCardsDesc[0].id).toEqual(8);
    expect(newCardsDesc[0].name).toEqual(
      "Structure and Interpretation of Computer Programs"
    );
  });

  it("contains 9 products", () => {
    const NUMBER_OF_PRODUCTS = 9;
    const wrapper = shallow(<ProductComponent />);
    expect(wrapper.dive().find(".product").length).toEqual(NUMBER_OF_PRODUCTS);
  });
});
