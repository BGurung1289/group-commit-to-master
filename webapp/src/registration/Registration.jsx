 import React from 'react';

export default class Registration extends React.Component{

    handleSubmit = (event)=> {
        event.preventDefault();
        const data = new FormData(event.target);
        /*MAPPING URL NEEDS TO BE PUT HERE*/
        fetch ("http://localhost:8080//ucc//register",{
            method:'POST',
            body: data}).then(
                function (response) {
                    return response.json();
                })
                .then(function (myJson) {
                    console.log(myJson);
                    if (myJson.result === "fail") {
                        alert("failed to register");
                    } else if(myJson.result === "successful"){
                        alert("successful register");
                        //change below address for redirecting
                        document.location.href = 'http://www.mozilla.org';
                    }
                });
    }
    render(){
        return (
            <div className="register_page">
                <h1>Time to Register</h1>
                <div className="register_form">
                    <form onSubmit={this.handleSubmit}>
                        <input type="text" required placeholder="Email" name="email"/><br/>
                        <input type="text" required placeholder="First Name" name="firstName"/><br/>
                        <input type="text" required placeholder="Last Name" name="lastName"/><br/>
                        <input type="password" required placeholder="Password" name="password"/><br />
                        <input name="" type="submit" value="Submit" />
                    </form>
                </div>
            </div>
        )
    }
}