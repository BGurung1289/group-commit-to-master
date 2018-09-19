import React from "react";
import {Link} from "react-router-dom";
import Login from "../login/login";
import './main.css';

export default function Home() {
    return(
        <div>
            <h1>Welcome to Chi Unit</h1>
            <div> 
            <Login />             
                
            </div> 
            <br/>

        </div>
    )
}