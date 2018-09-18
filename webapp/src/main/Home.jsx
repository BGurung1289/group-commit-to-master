import React from "react";
import {Link} from "react-router-dom";

export default function Home() {
    return(
        <div className="w3-padding w3-display-middle">
            <h1>Welcome to Chi Unit</h1>
            <Link to='/login'>Login</Link>
            <br/>
            <Link to='/register'>Register</Link>
        </div>
    )
}