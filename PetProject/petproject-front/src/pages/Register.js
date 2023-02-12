
import '../css/Register.css'
import React, {useState} from "react";
export default function Register() {

    const [user,setUser] = useState({
        first_name:"",
        last_name:"",
        username:"",
        password:"",
        email:""
    })
    const {first_name, last_name, username, password, email} = user

    const onInputChange=(e)=>{
        e.preventDefault();
        setUser({...user,[e.target.name]:e.target.value})
    }



    return (
        <div className={"body-container"}>
            <div className="login-page">
                <div className="form">
                    <form className="register-form">
                        <input type="text" placeholder="First name" value={first_name} onChange={(e)=>onInputChange(e)}/>
                        <input type="text" placeholder="Last name" value={last_name} onChange={(e)=>onInputChange(e)}/>
                        <input type="text" placeholder="Username" value={username} onChange={(e)=>onInputChange(e)}/>
                        <input type="password" placeholder="password" value={password} onChange={(e)=>onInputChange(e)}/>
                        <input type="text" placeholder="email address" value={email} onChange={(e)=>onInputChange(e)}/>
                        <button>create</button>
                        <p className="message">Already registered? <a href="#">Sign In</a></p>
                    </form>
                    {/*<form className="login-form">*/}
                    {/*    <input type="text" placeholder="username"/>*/}
                    {/*    <input type="password" placeholder="password"/>*/}
                    {/*    <button>login</button>*/}
                    {/*    <p className="message">Not registered? <a href="#">Create an account</a></p>*/}
                    {/*</form>*/}
                </div>
            </div>

        </div>
    );
}