import React from 'react';
import { Link } from 'react-router-dom';
import { Route } from 'react-router-dom';
import CourseOverview from './CourseOverview';

const CourseList = (props) => {

  let output = [];

  let courseArray = props.courses;

  if (courseArray === undefined || courseArray.length < 1) {
      output += "You are not assigned to any courses.";
  }
  else {
    courseArray.forEach(course => {
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
    output
  );
  
}

export default CourseList;
