import React from "react";

export default class CourseDetails extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            courseId: props.match.params.courseId
        };
    }

    render() {
        const {courseId} = this.state;
        console.log("COURSEID "+ courseId);

        return (
            <div className="w3-display-middle">
                <h3> {courseId}</h3>
            </div>
        )
    }
}