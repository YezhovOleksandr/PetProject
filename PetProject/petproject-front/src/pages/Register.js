
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

            <div className="login-box">
                <h2>Register</h2>
                <form onSubmit={(e)=>onSubmit(e)}>>
                    <div className="user-box">
                        <input type="text" name={"first_name"} value={first_name} onChange={(e)=>onInputChange(e)}/>/>
                        <label>First name</label>
                    </div>
                    <div className="user-box">
                        <input type="text" name={"last_name"} value={last_name} onChange={(e)=>onInputChange(e)}/>/>
                        <label>Last name</label>
                    </div>
                    <div className="user-box">
                        <input type="text" name={"username"} value={username} onChange={(e)=>onInputChange(e)}/>/>
                        <label>Username</label>
                    </div>
                    <div className="user-box">
                        <input type="password" name={"password"} value={password} onChange={(e)=>onInputChange(e)}/>/>
                        <label>Password</label>
                    </div>
                    <div className="user-box">
                        <input type="password" name={"email"} value={email} onChange={(e)=>onInputChange(e)}/>/>
                        <label>Email</label>
                    </div>
                    <button type={"submit"}>
                        <span></span>
                        <span></span>
                        <span></span>
                        <span></span>
                        Submit
                    </button>
                </form>

            {/*<div className="login-page">*/}
            {/*    <div className="form">*/}
            {/*        <form className="register-form" onSubmit={(e)=>onSubmit(e)}>*/}
            {/*            <input type={"text"} placeholder="First name" name={"first_name"} value={first_name} onChange={(e)=>onInputChange(e)}/>*/}
            {/*            <input type={"text"} placeholder="Last name" name={"last_name"} value={last_name} onChange={(e)=>onInputChange(e)}/>*/}
            {/*            <input type={"text"} placeholder="Username"  name={"username"} value={username} onChange={(e)=>onInputChange(e)}/>*/}
            {/*            <input type={"password"} placeholder="password"  name={"password"} value={password} onChange={(e)=>onInputChange(e)}/>*/}
            {/*            <input type={"text"} placeholder="email address" name={"email"} value={email} onChange={(e)=>onInputChange(e)}/>*/}
            {/*            <button type={"submit"}>Register</button>*/}
            {/*            <p className="message">Already registered? <Link to={"/login"}>Sign In</Link></p>*/}
            {/*        </form>*/}

            {/*    </div>*/}
            {/*</div>*/}

        </div>
    );
}