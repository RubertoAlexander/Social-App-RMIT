import React from "react";
import { shallow, mount } from "enzyme";

import { MapComponent } from "./MapComponent.jsx";

describe("Map", () => {
  let wrapper;

  it("renders", () => {
    wrapper = mount(<MapComponent />);

    console.log(wrapper.debug());
  });

  it("contains the div with id map", () => {
    wrapper = shallow(<MapComponent />);

    expect(wrapper.find("#map").length).toEqual(1);
  });

  it("contains the map component", () => {
    wrapper = shallow(<MapComponent />);

    expect(wrapper.find("Map").length).toEqual(1);
  });
});
