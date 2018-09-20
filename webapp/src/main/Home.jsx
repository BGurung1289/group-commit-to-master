import React from "react";
import {Link} from "react-router-dom";
import Login from "../login/login";
import './main.css';

export default function Home() {
    return(
        <div>
            <header> 
              Welcome to Chi Unit </header>
           
            <div> 
            <Login />             
                
            </div> 
            <br/>

        </div>
    )
}