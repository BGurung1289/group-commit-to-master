import React from "react";
import {Link} from "react-router-dom";

export default function Header() {
    return(
        <div className="w3-display-container">
            <div className="w3-padding w3-display-topleft">
                <Link to='/'>Logo</Link>
            </div>

            <p className="w3-padding w3-display-topmiddle">Search bar</p>

            <nav className="w3-padding w3-display-topright">
                <button>Navigation</button>
            </nav>
        </div>
    )
}