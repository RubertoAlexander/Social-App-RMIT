import React from "react";
import { mount, shallow } from "enzyme";

import { MapComponent } from "./MapComponent.jsx";

describe("Map", () => {
  let wrapper;

  it("renders", () => {
    let styles = [{}];
    wrapper = mount(<MapComponent classes={styles} />);

    console.log(wrapper.debug());
  });

  it("contains the wrapper with id map", () => {
    let styles = [{}];
    wrapper = shallow(<MapComponent classes={styles} />);

    expect(wrapper.find("#google-map").length).toEqual(1);
  });

  it("contains the map component", () => {
    let styles = [{}];
    wrapper = shallow(<MapComponent classes={styles} />);

    expect(wrapper.find("GoogleMap").length).toEqual(1);
  });

  it("Has a show classes button", () => {
    let styles = [{}];
    wrapper = shallow(<MapComponent classes={styles} />);
    const showClass = wrapper.find("#showClass");

    expect(showClass.length).toEqual(1);
  });

  it("Has a show users button", () => {
    let styles = [{}];
    wrapper = shallow(<MapComponent classes={styles} />);
    const showUsers = wrapper.find("#showUsers");

    expect(showUsers.length).toEqual(1);
  });
});
