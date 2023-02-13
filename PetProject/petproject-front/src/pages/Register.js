
import React, {useState} from "react";
import axios from "axios";
import {useNavigate, Link} from "react-router-dom";
export default function Register() {

    let navigate = useNavigate();

    const  [user,setUser]=useState({
        first_name:"",
        last_name:"",
        username:"",
        password:"",
        email:""
    })
    const{first_name, last_name, username, password, email} = user

    const onInputChange=(e ) => {
        setUser({...user, [e.target.name]:e.target.value})
    }

    const onSubmit= async (e) => {
        e.preventDefault()
        await axios.post("http://localhost:8080/register", user)
        navigate("/login")
    }


    return (
        <div className={"body-container"}>
            <div className="login-page">
                <div className="form">
                    <form className="register-form" onSubmit={(e)=>onSubmit(e)}>
                        <input type={"text"} placeholder="First name" name={"first_name"} value={first_name} onChange={(e)=>onInputChange(e)}/>
                        <input type={"text"} placeholder="Last name" name={"last_name"} value={last_name} onChange={(e)=>onInputChange(e)}/>
                        <input type={"text"} placeholder="Username"  name={"username"} value={username} onChange={(e)=>onInputChange(e)}/>
                        <input type={"password"} placeholder="password"  name={"password"} value={password} onChange={(e)=>onInputChange(e)}/>
                        <input type={"text"} placeholder="email address" name={"email"} value={email} onChange={(e)=>onInputChange(e)}/>
                        <button type={"submit"}>Register</button>
                        <p className="message">Already registered? <Link to={"/login"}>Sign In</Link></p>
                    </form>

                </div>
            </div>

        </div>
    );
}