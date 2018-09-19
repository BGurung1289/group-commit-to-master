import React from "react";

export default class CourseDetails extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            courseId: props.match.params.courseId,
            courseDetails: null,
            moduleDetails: null,
            sectionDetails: null
        };
    }

    componentDidMount() {
        const {courseId} = this.state;
        fetch("http://localhost:8080/course/" + courseId)
            .then(function (response) {
                return response.json();
            }).then(courseDetails => {
            this.setState({courseDetails: courseDetails.value})
        })
    }

    render() {
        const {courseDetails} = this.state;
        console.log(courseDetails)
        const course = Object.create(courseDetails);

        return (
            <div className="w3-content">
                <div>
                    <h3>Course: {course.courseName}</h3>
                    <h4>Course Description:</h4>
                    <p>{course.courseDescription}</p>
                </div>
                <div>
                    <h3>Modules: </h3>
                </div>
            </div>
        )
    }
}