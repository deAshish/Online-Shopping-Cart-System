import Login from "./Login/Login";
import { Link, Route, Routes } from "react-router-dom";
import Homepage from "./Homepage";
import ProductsShow from "./Product/ProductsShow";
import "./App.css";
import ExamPrep from "./ExamPrep";
import { useEffect } from "react";
import { useState } from "react";
import Product from "./Product/Product";
import Orders from "./Order/Orders";
import MyCart from "./Order/Mycart";

function Dashboard() {
  let [user, setUser] = useState({
    username: "Unknown",
  });

  let [userState, setUserState] = useState("logoff");

  const token = localStorage.getItem("authorization");

  useEffect(() => {
    // console.log("changing username")
    let tmpuser = localStorage.getItem("username");
    if (!tmpuser) {
      tmpuser = "Unknown";
    }
    setUser({ username: tmpuser });
  }, [userState]);

  function userToggle() {
    if (userState == "logged") {
      setUserState("logoff");
    } else {
      setUserState("logged");
    }
    // console.log("toggle button")
    localStorage.removeItem("username");
    localStorage.removeItem("authorization");
  }

  return (
    <div className="App">
      <ul id="menu">
        <li>
          <Link to="/">Homepage</Link>
        </li>
        <li>
          <Link to="/login">Login</Link>
        </li>
        <li>
          <Link to="/products">Products</Link>
        </li>
        <li>
          <Link to="/productAdd">Product Add</Link>
        </li>
        <li>
          <Link to="/orders">Orders</Link>
        </li>
        <li>
          <Link to="/mycart">
            <img src="./image/cart.png" className="mycart" />
            My cart
          </Link>
        </li>
        <li id="user">[Hello {user.username}]</li>
        <input type="button" onClick={userToggle} value="Logout" />
      </ul>

      <Routes>
        <Route
          path="/login"
          element={<Login setUserState={setUserState} userState={userState} />}
        />

        <Route path="/exam" element={<ExamPrep />} />

        <Route path="/" element={<Homepage />} />

        <Route path="/products" element={<ProductsShow />} />

        <Route path="/productAdd" element={<Product />} />

        <Route path="/orders" element={<Orders />} />

        <Route path="/mycart" element={<MyCart />} />

        <Route path="/exam" element={<ExamPrep />} />
      </Routes>
    </div>
  );
}

export default Dashboard;
