import {Link} from "react-router-dom";
import Login from "../pages/Login";

export default function Navbar() {
    return (
        <div>
            <nav className="navbar navbar-expand navbar-dark bg-dark" aria-label="Second navbar example">
                <div className="container-fluid">
                    <Link to={"/"} className={"navbar-brand"}>Car store</Link>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarsExample02">
                        <ul className="navbar-nav me-auto">
                            <li className="nav-item">
                                <a className="nav-link active" aria-current="page" href="#">Home</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">Link</a>
                            </li>
                        </ul>
                        <Link to={"/login"} className={"btn btn-outline-success me-2"}>Login</Link>
                        <Link to={"/register"} className={"btn btn-outline-danger me-2"}>Register</Link>
                    </div>
                </div>
            </nav>
        </div>
    );
}