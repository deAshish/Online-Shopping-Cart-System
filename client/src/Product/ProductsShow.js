import axios from "axios";
import { useEffect, useState, createContext } from "react";
import ProductList from "./ProductList";
import "./product.css";

export const ResponseContext = createContext();

function ProductsShow() {
  const saved_token = localStorage.getItem("authorization");
  const AuthStr = "Bearer ".concat(saved_token);
  const [actionResponse, setActionResponse] = useState("");

  let [productsState, setProductsState] = useState({
    products: [
      {
        id: "xx",
        productCategory: "xx",
        name: "xx",
        addedDate: "xx",
        price: "xx",
        productStatus: "xx",
      },
    ],
  });

  function deleteProduct(id) {
    const saved_token = localStorage.getItem("authorization");
    const AuthStr = "Bearer ".concat(saved_token);

    axios
      .delete("http://localhost:8080/products/" + id, {
        headers: {
          Authorization: AuthStr,
        },
      })
      .then((res) => {
        console.log("Success: ", res);
        if (!res.data) {
          setActionResponse("Product cannot be deleted.");
        } else setActionResponse("Product deleted.");
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }

  function refresh() {
    const saved_token = localStorage.getItem("authorization");
    const AuthStr = "Bearer ".concat(saved_token);
    axios
      .get("http://localhost:8080/products/getAll", {
        headers: {
          Authorization: AuthStr,
        },
      })
      .then((res) => {
        setProductsState({ products: res.data });
      });
  }

  useEffect(() => {
    refresh();
  }, []);

  useEffect(() => {
    refresh();
  }, [actionResponse]);

  return (
    <ResponseContext.Provider value={actionResponse}>
      <div className="responseMessage">{actionResponse}</div>
      <div>
        {productsState.products.map((item, index) => {
          return (
            <ProductList
              id={item.id}
              productCategory={item.productCategory}
              name={item.name}
              addedDate={item.addedDate}
              price={item.price}
              productStatus={item.productStatus}
              index={index}
              imagePath={item.imagePath}
              deleteProduct={deleteProduct}
            />
          );
        })}
      </div>
    </ResponseContext.Provider>
  );
}

export default ProductsShow;
