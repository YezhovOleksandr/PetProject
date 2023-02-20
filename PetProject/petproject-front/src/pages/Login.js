import {Link} from "react-router-dom";
import "./Login.css"

export default function Login() {
    return (
        <div>
        <div className="login-box">
            <h2>Login</h2>
            <form>
                <div className="user-box">
                    <input type="text"/>
                        <label>Username</label>
                </div>
                <div className="user-box">
                    <input type="password"/>
                        <label>Password</label>
                </div>
                <button>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    Submit
                </button>
            </form>
        </div>
        </div>
    );
}