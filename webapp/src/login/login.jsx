import React, { Component } from 'react';


class Login extends Component {
    handleSubmit = event => {
        event.preventDefault();
        const loginData = new FormData(event.target);

        fetch("http://localhost:8080/login", {
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
                    sessionStorage.userid = myJson.id;
                    sessionStorage.name = myJson.name;
                    sessionStorage.utype = myJson.type;

                    //change below address for redirecting
                    document.location.href = 'http://www.mozilla.org';
                } else{
                    document.getElementById("result").innerHTML = "Email does not exist";
                }
            });
    }


    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <input type="email" placeholder="Email" name="email" required />
                <input type="password" placeholder="Password" name="password" />
                <div id="result"></div>
                <input type="submit" value="Login" />
            </form>
        )
    }
}

export default Login;