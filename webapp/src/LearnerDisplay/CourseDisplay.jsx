import React from 'react';
import Test from './Test';
import Section from './Section';

const CourseDisplay = (props) => {

  let output = [];


          console.log("HEEEJ" + props.state.course);

  if (props.state.moduleSections === "" || props.state.moduleSections === null) {
    output += "Module not found!"
  }
  else if (props.state.section > props.state.moduleSections.length) {
    output += <Test currentModule = {props.state.module} />
  }
  else {
    output +=
        <div className = "section">
          <Section currentSection = {props.state.moduleSections[props.state.section]} />
          <button onClick={props.updateSection}>Next</button>
        </div>
  }


  return (
    output
  );

}

export default CourseDisplay;
