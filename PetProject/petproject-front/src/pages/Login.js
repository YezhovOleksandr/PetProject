import "../css/Register.css"

export default function Login() {
    return (
        <div className={"login-page body-container"}>
            <form className="form">
                <input type="text" placeholder="username"/>
                <input type="password" placeholder="password"/>
                <button>login</button>
                <p className="message">Not registered? <a href="#">Create an account</a></p>
            </form>
        </div>
    );
}