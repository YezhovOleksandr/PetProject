import {Link} from "react-router-dom";

export default function Login() {
    return (
        <div className={"login-page body-container"}>
            <form className="form">
                <input type="text" placeholder="username"/>
                <input type="password" placeholder="password"/>
                <button>login</button>
                <p className="message">Not registered? <Link to={"/register"}>Create an account</Link></p>
            </form>
        </div>
    );
}