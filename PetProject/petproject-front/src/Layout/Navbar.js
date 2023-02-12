import {Link} from "react-router-dom";

export default function Navbar() {
    return (
        <div>
            <nav className="navbar bg-primary">
                <div className="container-fluid">
                    <Link className="navbar-brand mb-0 h1" to={"/"}>Navbar</Link>
                    <Link className="btn btn-danger" to={"/login"}>Login</Link>
                    <Link className="btn btn-warning" to={"/register"}>Register</Link>
                </div>

            </nav>
        </div>
    );
}