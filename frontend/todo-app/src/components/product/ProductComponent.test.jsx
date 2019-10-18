import React from "react";
import ProductComponent from "./ProductComponent";
import { shallow } from "enzyme";

describe("ProductComponent", () => {
  const mockCards = [
    {
      id: 1,
      productName: "Programmer Guide",
      description: "blablablablabla"
    },
    {
      id: 2,
      productName: "Elephant Book",
      description: "blablablablabla"
    },
    {
      id: 3,
      productName: "Self taught programmer",
      description: "blablablablabla"
    },
    {
      id: 4,
      productName: "Computer Science Book",
      description: "blablablablabla"
    },
    {
      id: 5,
      productName: "Beginning Programming Reference for dummies",
      description: "blablablablabla"
    },
    {
      id: 6,
      productName: "Computer Science Distilled",
      description: "blablablablabla"
    },
    {
      id: 7,
      productName: "Computer science principles",
      description: "blablablablabla"
    },
    {
      id: 8,
      productName: "Structure and Interpretation of Computer Programs",
      description: "blablablablabla"
    },
    {
      id: 9,
      productName: "AP Computer Science A",
      description: "blablablablabla"
    }
  ];

  let wrapper;

  beforeEach(() => {
    wrapper = shallow(<ProductComponent cards={mockCards} />);
  });

  it("renders without crashing", () => {
    shallow(<ProductComponent />);
  });

  it("sorts products ascending if products are unsorted", () => {
    const newCards = wrapper
      .dive()
      .instance()
      .sortProduct(mockCards);
    expect(newCards[0].id).toEqual(9);
    expect(newCards[0].productName).toEqual("AP Computer Science A");
  });

  it("sorts products descending if products are sorted ascending", () => {
    const productsSortedDesc = [
      {
        id: 9,
        productName: "AP Computer Science A",
        description: "blablablablabla"
      },
      {
        id: 5,
        productName: "Beginning Programming Reference for dummies",
        description: "blablablablabla"
      },
      {
        id: 4,
        productName: "Computer Science Book",
        description: "blablablablabla"
      },
      {
        id: 6,
        productName: "Computer Science Distilled",
        description: "blablablablabla"
      },
      {
        id: 7,
        productName: "Computer science principles",
        description: "blablablablabla"
      },
      { id: 2, productName: "Elephant Book", description: "blablablablabla" },
      {
        id: 1,
        productName: "Programmer Guide",
        description: "blablablablabla"
      },
      {
        id: 3,
        productName: "Self taught programmer",
        description: "blablablablabla"
      },
      {
        id: 8,
        productName: "Structure and Interpretation of Computer Programs",
        description: "blablablablabla"
      }
    ];

    const newCardsDesc = wrapper
      .dive()
      .instance()
      .sortProduct(productsSortedDesc);

    const expectedResult = [
      {
        id: 8,
        productName: "Structure and Interpretation of Computer Programs",
        description: "blablablablabla"
      },
      {
        id: 3,
        productName: "Self taught programmer",
        description: "blablablablabla"
      },
      {
        id: 1,
        productName: "Programmer Guide",
        description: "blablablablabla"
      },
      { id: 2, productName: "Elephant Book", description: "blablablablabla" },
      {
        id: 7,
        productName: "Computer science principles",
        description: "blablablablabla"
      },
      {
        id: 6,
        productName: "Computer Science Distilled",
        description: "blablablablabla"
      },
      {
        id: 4,
        productName: "Computer Science Book",
        description: "blablablablabla"
      },
      {
        id: 5,
        productName: "Beginning Programming Reference for dummies",
        description: "blablablablabla"
      },
      {
        id: 9,
        productName: "AP Computer Science A",
        description: "blablablablabla"
      }
    ];
    expect(newCardsDesc).toEqual(expectedResult);
  });

  it("sorts products ascending if products are sorted descending", () => {
    const productsSortedAsc = [
      {
        id: 8,
        productName: "Structure and Interpretation of Computer Programs",
        description: "blablablablabla"
      },
      {
        id: 3,
        productName: "Self taught programmer",
        description: "blablablablabla"
      },
      {
        id: 1,
        productName: "Programmer Guide",
        description: "blablablablabla"
      },
      { id: 2, productName: "Elephant Book", description: "blablablablabla" },
      {
        id: 7,
        productName: "Computer science principles",
        description: "blablablablabla"
      },
      {
        id: 6,
        productName: "Computer Science Distilled",
        description: "blablablablabla"
      },
      {
        id: 4,
        productName: "Computer Science Book",
        description: "blablablablabla"
      },
      {
        id: 5,
        productName: "Beginning Programming Reference for dummies",
        description: "blablablablabla"
      },
      {
        id: 9,
        productName: "AP Computer Science A",
        description: "blablablablabla"
      }
    ];

    const newCards = wrapper
      .dive()
      .instance()
      .sortProduct(productsSortedAsc);

    const expectedResult = [
      {
        id: 9,
        productName: "AP Computer Science A",
        description: "blablablablabla"
      },
      {
        id: 5,
        productName: "Beginning Programming Reference for dummies",
        description: "blablablablabla"
      },
      {
        id: 4,
        productName: "Computer Science Book",
        description: "blablablablabla"
      },
      {
        id: 6,
        productName: "Computer Science Distilled",
        description: "blablablablabla"
      },
      {
        id: 7,
        productName: "Computer science principles",
        description: "blablablablabla"
      },
      { id: 2, productName: "Elephant Book", description: "blablablablabla" },
      {
        id: 1,
        productName: "Programmer Guide",
        description: "blablablablabla"
      },
      {
        id: 3,
        productName: "Self taught programmer",
        description: "blablablablabla"
      },
      {
        id: 8,
        productName: "Structure and Interpretation of Computer Programs",
        description: "blablablablabla"
      }
    ];

    expect(newCards).toEqual(expectedResult);
  });

  it("handles null products", () => {
    const invalidCards = wrapper
      .dive()
      .instance()
      .sortProduct(null);
    expect(invalidCards).toEqual(undefined);
  });

  it("handles invalid products with no name", () => {
    const invalidProduct = [
      {
        id: 1
      }
    ];
    const invalidProducts = wrapper
      .dive()
      .instance()
      .sortProduct(invalidProduct);
    expect(invalidProducts).toEqual(undefined);
  });

  it("view button should exist for every product", () => {
    const childComponent = wrapper.dive();
    const viewButton = childComponent.find(".view-button");
    expect(viewButton.length).toEqual(9);
  });

  it("edit button should exist for every product", () => {
    const childComponent = wrapper.dive();
    const viewButton = childComponent.find(".edit-button");
    expect(viewButton.length).toEqual(9);
  });

  it("contains 9 products", () => {
    const NUMBER_OF_PRODUCTS = 9;
    expect(wrapper.dive().find(".product").length).toEqual(NUMBER_OF_PRODUCTS);
  });

  //new test for display price of product
  it("price of product should display", () => {
    const childComponent = wrapper.dive();
    const priceOfProducts = childComponent.find(".product-price");
    expect(priceOfProducts.length).toEqual(9);
  });

  it("price of each product should display correctly", () => {});
});
