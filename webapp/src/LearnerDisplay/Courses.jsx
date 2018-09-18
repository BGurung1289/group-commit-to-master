import React from "react";
import {Link} from 'react-router-dom';

export default class Courses extends React.Component {
    state = {
        courses: null
    };


    // courseValues;
    componentDidMount() {
        console.log("MOUNTED")
        this.courseValues = fetch("http://localhost:8080/course/searchCourse")
            .then(function (response) {
                return response.json()
            }).then(courses => {
                this.setState({courses})
            });
        console.log(this.state.courses)
    }

    render() {
        const {courses} = this.state;
        let temp;
        return (
            <div className="courses w3-display-middle">
                <h2>Course List</h2>
                {courses ? (
                    courses.map(course => (
                            temp = '/showcourse/' + course.courseId,
                                <Link to={temp} key={course.courseId}>
                                    {course.courseName}
                                </Link>
                        )
                    )
                ) : false}
            </div>
        )
    }
}


    
