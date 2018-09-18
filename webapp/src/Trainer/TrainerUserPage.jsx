import React from "react";
import {Link} from "react-router-dom";

export class TrainerUserPage extends React.Component{

    render(){
        return(
            <div className="trainerPage w3-padding w3-display-middle">
                <h2>Welcome Trainer</h2>
                <Link to="/addCourse">Add Course</Link>
            </div>
        )
    }
}