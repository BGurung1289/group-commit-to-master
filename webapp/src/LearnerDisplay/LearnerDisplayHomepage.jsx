import React from 'react';

import { BrowserRouter as Router } from 'react-router-dom';
//import { Route } from 'react-router-dom';
//import Course from './Course';
//import GenerateOutput from './GenerateOutput';
import CourseList from './CourseList';

let courses;

export default class LearnerDisplayHomepage extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      user: 1,
      courses
    };

//    this.generateOutput = this.generateOutput.bind(this);
  }

  async componentDidMount() {
    let userCourses;// = this.state.courses;
    try {
      const learnerUrl = `http://localhost:8080/user/${this.state.user}/getCourses`;
      let learnerResponse = await fetch(learnerUrl);
      let learnerResponseJSON = await learnerResponse.json();
      userCourses = await learnerResponseJSON;
    }
    catch(e) {
      userCourses = ""; //You are not assigned to any courses.
    }
    this.setState({
      courses: userCourses
    });
  }

/*  generateOutput(courses) {
    let output = "";

    if (courses === "You are not assigned to any courses." || courses === null || courses === "") {
        output = "HEJ"; //"<div>{state.courses}</div>";
    }
    else {
      output = "HEPP";
      courses.forEach(course => {
        output += course.id;  /*`<li>
          <Link className = "courseLink" to = {/course/${courses.id}} course = ${courses.id}>${courses.name}Test</Link>
        </li>`;*/
    /*  })
    }
    return output;
  }*/

  render() {
console.log(this.state.courses);
    return (
      <div className="homepage">
        <div className="header">
          <h1>My courses</h1>
        </div>
        <Router>
          <div className="coursesList"><ul>
          <CourseList courses = {this.state.courses} />
          </ul></div>
        </Router>
      </div>
    );
  }
}
