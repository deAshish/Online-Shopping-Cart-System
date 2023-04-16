import React, { Component, useState } from "react";
import axios from "axios";
// import Homepage from "../Homepage";
// import { Link, Route, Routes, Navigate } from "react-router-dom";
import "./Login.css";

function Login(props) {
  let [state, setState] = useState({
    username: "",
    password: "",
  });

  function handleFormSubmit(event) {
    event.preventDefault();

    const endpoint = "http://localhost:8080/authenticate";

    const user_object = {
      username: state.username,
      password: state.password,
    };

    axios.post(endpoint, user_object).then((res) => {
      if (Object.keys(Object.values(res)[0]) == "jwt") {
        localStorage.setItem("authorization", res.data.jwt);
        localStorage.setItem("username", state.username);

        props.setUserState("logged");
        handleDashboard();
        window.location = "/homepage";
      } else {
        alert("Login has failed, check you USERNAME/PASSWORD");
      }
      // console.log(res.data.jwt);
    });
  }

  function usernameChangedEvent(event) {
    // event.targete.value
    setState({ ...state, username: event.target.value });
  }

  function passwordChangedEvent(event) {
    // event.targete.value
    setState({ ...state, password: event.target.value });
  }

  function handleDashboard() {
    const saved_token = localStorage.getItem("authorization");
    const AuthStr = "Bearer ".concat(saved_token);

    console.log("got token: ", AuthStr);

    axios
      .get("http://localhost:8080/products/getAll", {
        headers: {
          Authorization: AuthStr,
        },
      })
      .then((res) => {
        console.log(res.data);
      });
  }

  return (
    <div className="App">
      <div class="login-wrapper">
        <form class="form-signin" onSubmit={handleFormSubmit}>
          <h2 class="form-signin-heading">Please login</h2>
          <div className="form-group">
            <input
              type="text"
              class="form-control"
              placeholder="User name"
              onChange={usernameChangedEvent}
            />
          </div>
          <div className="form-group">
            <input
              type="password"
              class="form-control"
              placeholder="password"
              onChange={passwordChangedEvent}
            />
          </div>
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Login
          </button>
        </form>
      </div>
    </div>
  );
}

export default Login;
