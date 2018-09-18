import React from 'react';
import { Link } from 'react-router-dom';


const CourseList = (props) => {
  let output = "";

  let testArray = props.courses;
  console.log(testArray);


  if (testArray === undefined || testArray.length < 1) { //props.courses === null || props.courses === '' || props.courses === undefined || props.length === 0) {
      output += "You are not assigned to any courses.";
  }
  else {

    /*  for (var i = 0; i < props.courses.length; i++) {
        testArray.push(//<li>
          //<Link className = "courseLink" to = {`/course/${course.id}`} course = {`${course.id}`}>
          //{course["key"]}
          <div>
          props.courses[i].name[value]
          </div>
          //</Link>
        //</li>;

        );
      }*/

      //testArray.forEach(course => {

  //  output += <ul>;
    //for (var course in testArray) {
    testArray.forEach(course => {
      var courseId = JSON.stringify(course.id);
      var courseName = JSON.stringify(course.name);

      console.log(courseId);
      console.log(courseName);

      output +=// <li>
      //  <Link className = "courseLink" to = "/course/"+{courseId} course = {courseId}>
          courseName
      //  </Link>
    //  </li>
    })
    console.log(output);
//    output += </ul>;



    /*for (var course in props.courses) {
//    {props.courses.forEach(course => {
      output += //<li>
        //<Link className = "courseLink" to = {`/course/${course.id}`} course = {`${course.id}`}>
        //{course["key"]}
        course.name
        //</Link>
      //</li>;
    }//)}
    */
  }

  return (
    output
  );
}

export default CourseList;
