import React from "react";
import {Link} from "react-router-dom";
import {Popover} from "react-bootstrap";
import OverlayTrigger from "react-bootstrap/es/OverlayTrigger";
import Button from "react-bootstrap/es/Button";

export class TrainerUserPage extends React.Component {
    state = {
        courses: null,
        trainerId: 1
    };

    componentDidMount() {
        fetch("http://localhost:8080/course/trainerCourses/1")
            .then(function (response) {
                return response.json()
            }).then(courses => {
            this.setState({courses})
        });

    }

    render() {
        console.log(this.state.courses);
        const {courses, trainerId} = this.state;
        let courseModuleUrl;
        let pop;
        let addModuleUrl;
        let addCourseUrl = "/addCourse/" + trainerId;

        return (
            <div className="trainerPage w3-content w3-padding">
                <h2>Welcome Trainer</h2>

                <div>
                    <h3>Add Course</h3>
                    <Link to={addCourseUrl}>Add Course</Link>
                </div>

                <div>
                    <h3>Your Courses</h3>
                    <ul>
                        {courses ? (
                            courses.map(course => (
                                    courseModuleUrl = "/trainerCourse/" + course.courseId,
                                        addModuleUrl = "/addModule/" + course.courseId,
                                        pop = (<Popover id="popover-positioned-right" title="Options">
                                            <Link to={"/showcourse/"+course.courseId}>View Course details</Link>
                                            <br/>
                                            <Link to={courseModuleUrl} key={course.courseId}>Modules</Link>
                                            <br/>
                                            <Link to={addModuleUrl}>Add Module</Link>
                                        </Popover>),
                                        <li key={course.courseId}><OverlayTrigger rootClose trigger="click"
                                                                                  placement="right"
                                                                                  key={course.courseId} overlay={pop}>
                                            <Button>{course.courseName}</Button>
                                        </OverlayTrigger></li>
                                )
                            )
                        ) : <h4>No Courses to display</h4>}
                    </ul>
                </div>
            </div>
        )
    }
}