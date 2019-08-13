import React from "react";
import ProductComponent from "./ProductComponent";
import { mount, shallow } from "enzyme";

it("renders without crashing", () => {
  shallow(<ProductComponent />);
});

it("renders without crashing", () => {
  const wrapper = mount(<ProductComponent />);
  console.log(wrapper.html());
});
