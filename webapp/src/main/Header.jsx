import React from "react";
import {Link} from "react-router-dom";
import Logo from "../logo/CHI Unit logo dark.png";

export default function Header() {
    return(
        <div className="w3-display-container">
            <div className="w3-padding w3-display-topleft">
                <Link to='/'><img className="img-responsive" src={Logo} alt="logo" height="45" width="45"/></Link>
            </div>

            <p className="w3-padding w3-display-topmiddle">Search bar</p>

            <nav className="w3-padding w3-display-topright">
                <Link to='/courses'>Courses</Link>
                <br/>
                <Link to='/trainerPage'>Trainer</Link>
            </nav>
        </div>
    )
}