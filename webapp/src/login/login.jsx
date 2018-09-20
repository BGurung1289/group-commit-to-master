import React, { Component } from 'react';
import '../logreg.css';
import {Link} from "react-router-dom";


class Login extends Component {
    handleSubmit = event => {
        event.preventDefault();
        const loginData = new FormData(event.target);

        fetch("http://localhost:8080/ucc/login", {
            method: 'POST',
            body: loginData
        }).then(
            function (response) {
                return response.json();
            })
            .then(function (myJson) {
                if (myJson.result === "fail") {
                    document.getElementById("result").innerHTML = "Wrong email or password";
                } else if(myJson.result === "success"){
                    document.getElementById("result").innerHTML = "Welcome " + myJson.name;

                    //change below address for redirecting
                    document.location.href = 'http://localhost:3000/courses';
                } else{
                    document.getElementById("result").innerHTML = "Email does not exist";
                }
            });
    }
    render() {
        return (
           <div className ="form-container">
                <h1>Login</h1>
                <form onSubmit={this.handleSubmit}>
                    <div className = "form-group">
                    <input type="email" className="form-control" placeholder="Email" name="email" required /> 
                        </div>
                      <div className = "form-group"> 
                          <input type="password" className="form-control" placeholder="Password" name="password" />
                    </div>
                    <div id="result"></div>
                    <div>
                    <input id = "login-btn" className = "btn btn-primary" type="submit" value="Login" />
                    </div>
                    <div className = "theLink "> 
                     <Link to='/register'>Looking to register? </Link>
                        </div>
                </form>
            </div>      
        );
    }
}

export default Login;