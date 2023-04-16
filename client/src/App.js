import "./App.css";

import { BrowserRouter } from "react-router-dom";
import Dashboard from "./Dashboard";

function App() {
  //--------------Model

  //--------------View
  return (
    <BrowserRouter>
      <div className="App">
        <Dashboard />
      </div>
    </BrowserRouter>
  );
}

export default App;
