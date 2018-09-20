import React from 'react';
import { Link } from 'react-router-dom';
import { Route } from 'react-router-dom';
import Module from './Module';

const ModuleList = (props) => {

  let output = [];

  let moduleArray = props.state.modules;
  console.log("MODULE" + JSON.stringify(moduleArray));
  console.log("KURS: " + props.state.course);
  let courseId = props.state.course;

  if (moduleArray === undefined || moduleArray.length < 1) {
      output += "No modules found.";
  }
  else {
    moduleArray.forEach(module => {
      output.push(
        <li>
          <Link className = "courseLink" to = {`/course/${props.state.course}/module/${module.id}`} module = {module.id} >
            {module.name}
          </Link>
          <React.Fragment>
            <Route path = {`/course/${props.state.course}/module/${module.id}`} render = {(props) => <Module {...props} course = {courseId} module = {module.id} /> }  />
          </React.Fragment>
        </li>
      );
    })
  }

  return (
    output
  );

}

export default ModuleList;
