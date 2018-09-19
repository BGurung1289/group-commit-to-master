import React from "react";
import {Link} from 'react-router-dom';

export default class Courses extends React.Component {
    state = {
        courses: null
    };

    componentDidMount() {
        fetch("http://localhost:8080/course/searchCourse")
            .then(function (response) {
                return response.json()
            }).then(courses => {
            this.setState({courses})
        });
    }

    render() {
        const {courses} = this.state;
        let temp;
        return (
            <div className="courses w3-display-middle">
                <h2>Course List</h2>
                <ul>
                {courses ? (
                    courses.map(course => (
                            temp = "/courseDetails/" + course.courseId,
                               <li key={course.courseId}> <Link to={temp} key={course.courseId}>
                                    {course.courseName}
                               </Link> </li>
                        )
                    )
                ) : false}
            </ul>
            </div>
        )
    }
}


    
