import logo from './logo.svg';
import './App.css';
import Navbar from "./Layout/Navbar";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Home from "./pages/Home";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar/>
          <Routes>
              <Route exact path={"/"} element={<Home/>}/>
              <Route exact path={"/register"} element={<Register/>}/>
              <Route exact path={"/login"} element={<Login/>}/>
          </Routes>
      </Router>
    </div>
  );
}

export default App;
