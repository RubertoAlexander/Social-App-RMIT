import React from "react";

import { shallow } from "enzyme";
import LoginComponent from "./LoginComponent";

describe("LoginComponent", () => {
  const mockSuccessLogin = [
    {
      id: 9,
      name: "AP Computer Science A",
      description: "blablablablabla"
    }
  ];

  it("renders without crashing", () => {
    shallow(<LoginComponent />);
  });
});
