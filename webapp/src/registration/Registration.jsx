 import React from 'react';
import {Link} from "react-router-dom";
import '../logreg.css';

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
                        document.location.href = 'http://localhost:3000/courses';
                    }
                });
    }
    render(){
        return (

            <div className = "form-container">
                <h1>Time to Register</h1>
                    <form onSubmit={this.handleSubmit}>
                        <div className = "form-group"> 
                            <input className="form-control" type="text" required placeholder="Email" />
                        </div>
                        
                        <div className = "form-group">
                         <input className="form-control" type="text" required placeholder="First Name" />
                        </div>
                        
                        <div className = "form-group">
                         <input className="form-control" type="text" required placeholder="Last Name" />
                        </div>
                        
                       <div className = "form-group">
                         <input className="form-control" type="password" required placeholder="Password" />
                        </div>
                        
                        <input  id = "register-btn" className = "btn btn-primary" type="submit" value="Submit" /> 
                        <div className = "theLink"> 
                        <Link to='/login'> Looking to login? </Link>
                        </div>
                    </form>
                
                </div>
        )
    }
}