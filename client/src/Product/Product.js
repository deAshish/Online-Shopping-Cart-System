import axios from "axios";
import React, { useEffect, useState } from "react";
// import ComboBox from "react-responsive-combo-box";
// import "react-responsive-combo-box/dist/index.css";
import "../Product/product.css";

function Product() {
  //--------------Model
  const [productCategory, setProductCategory] = useState({
    category: ["Clothing", "Computer", "Book"],
  });

  const [selectedCategory, setSelectedCategory] = useState({});

  const [addResponse, setAddResponse] = useState();

  const [inputState, setInputState] = useState({
    prname: "",
    prprice: 0.0,
    img: "./image/def.png",
  });

  const saved_token = localStorage.getItem("authorization");
  const AuthStr = "Bearer ".concat(saved_token);

  //--------------Functions
  function populateCategory() {
    axios
      .get("http://localhost:8080/products/getAllCategory", {
        headers: { Authorization: AuthStr },
      })
      .then((res) => {
        setProductCategory({ category: res.data });
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }

  function addNewProduct() {
    const product = {
      name: inputState.prname,
      price: inputState.prprice,
      productCategory: selectedCategory,
      user: { id: 1 },
      imagePath: inputState.img,
    };

    console.log(product);
    axios
      .post("http://localhost:8080/products", product, {
        headers: { Authorization: AuthStr },
      })
      .then((res) => {
        console.log("Product has been added successfully", res.data);
        setAddResponse("Product has been added successfully");
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }

  function onChangeProductName(e) {
    setInputState({ ...inputState, prname: e.target.value });
    setAddResponse("");
  }

  function onChangeProductPrice(e) {
    setInputState({ ...inputState, prprice: e.target.value });
    setAddResponse("");
  }

  //--------------Operation
  useEffect(() => {
    // localStorage.setItem("jwt", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYzOTYwOTc0NiwiZXhwIjoxNjM5NjI3NzQ2fQ.0Hi1HNx8ur9tgnwHNUKpW8EOINMu7ZCm6ad9_smb8-g");
    // localStorage.setItem("user", "admin");

    populateCategory();
  }, []);

  //--------------View
  return (
    <div>
      <div className="productForm">
        <h2>Create new Product</h2>
        Name:
        <input type="text" onChange={onChangeProductName} />
        Price:
        <input type="text" onChange={onChangeProductPrice} />
        Select catalog:
        {/* <ComboBox
          options={productCategory.category}
          name="category"
          enableAutocomplete
          onSelect={setSelectedCategory}
        /> */}
        <input type="submit" value="Add product" onClick={addNewProduct} />
        <br />
        <span className="responseMessage">{addResponse}</span>
      </div>
    </div>
  );
}

export default Product;
