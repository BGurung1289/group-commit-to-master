import React from 'react';
import { Link } from 'react-router-dom';
import { Route } from 'react-router-dom';
import CourseOverview from './CourseOverview';
import { BrowserRouter as Router } from 'react-router-dom';

let courses;

export default class LearnerDisplayHomepage extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      user: 1,
      courses
    };
  }

  async componentDidMount() {
    let userCourses;
    try {
      const learnerUrl = `http://localhost:8080/user/${this.state.user}/getCourses`;
      let learnerResponse = await fetch(learnerUrl);
      let learnerResponseJSON = await learnerResponse.json();
      userCourses = await learnerResponseJSON;
    }
    catch(e) {
      userCourses = "";
    }
    this.setState({
      courses: userCourses
    });
  }

  render() {
    let output = [];

    if (this.state.courses === undefined || this.state.courses.length < 1) {
        output += "You are not assigned to any courses.";
    }
    else {
      this.state.courses.forEach(course => {
        output.push(
          <li>
            <Link className = "courseLink" to = {`/course/${course.id}`} course = {course.id}>
              {course.name}
            </Link>
            <React.Fragment>
              <Route path = {`/course/${course.id}`} render = {(props) => <CourseOverview {...props} course = {course.id} /> }  />
            </React.Fragment>
          </li>
        );
      })
    }

    return (
      <div className="homepage">
        <div className="header">
          <h1>My courses</h1>
        </div>
        <Router>
          <div className="coursesList">
            <ul>
              {output}
            </ul>
          </div>
        </Router>
      </div>
    );
  }

}
