import {Link} from "react-router-dom";

export default function Navbar() {
    return (
        <div>
            <nav className="navbar navbar-collapse bg-dark ">
                <div className="container-fluid">
                    <Link className="navbar-brand text-light h1" to={"/"}>Navbar</Link>
                    <Link className="btn btn-danger mb-0" to={"/login"}>Login</Link>
                    <Link className="btn btn-warning" to={"/register"}>Register</Link>
                </div>

            </nav>
        </div>
    );
}