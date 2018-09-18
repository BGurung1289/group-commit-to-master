import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';


const GenerateOutput = (props) => {
  let output = "hej";

  if (courses === "You are not assigned to any courses." || courses === null || courses === "") {
      output = "HEJ"; //"<div>{state.courses}</div>";
  }
  else {
    output = "HEPP";
    props.courses.forEach(course => {
      output += course.id;  /*`<li>
        <Link className = "courseLink" to = {/course/${props.id}} course = ${props.id}>${props.name}Test</Link>
      </li>`;*/
    });
  }

  return (
    output
  );
}

export default GenerateOutput;
