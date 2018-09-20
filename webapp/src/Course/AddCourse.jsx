import React from "react";

export default class AddCourse extends React.Component {
    constructor(props) {
        super(props);
        console.log(props.match)
        this.state = {
            trainerId: props.match.params.trainerId
        };
    }

    handleSubmit = (event) => {
        const {trainerId} = this.state;

        if (event.target.courseName.value.length !== 0 &&
            event.target.courseDescription.value.length !== 0) {
            const data = new FormData(event.target);

            data.append("madeByTrainerId", trainerId);

            fetch("http://localhost:8080/course/add", {
                method: 'POST',
                body: data
            }).then(function () {
                document.location.href = '/trainerPage';
            })

        } else {
            event.preventDefault();
            document.getElementById("noContinue").innerHTML = "Need to fill in all input";
        }
    };

    render() {
        return (
            <div id="addCourseDiv" className="addCourse w3-content">
                <h3>Add Course </h3>
                <form id="addCourseForm" onSubmit={this.handleSubmit}>
                    <label>Course Name</label>
                    <input name="courseName" type="text" placeholder="Enter course name"/>
                    <br/>
                    <label>Course description</label>
                    <textarea name="courseDescription" rows="10" cols="70" type="text"
                              placeholder="Enter course description"/>
                    <br/>
                    <div id="noContinue"></div>
                    <input type="submit" value="Add Course"/>
                </form>
            </div>
        )
    }
}
