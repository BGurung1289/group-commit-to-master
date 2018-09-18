import React from 'react';
import {Link} from 'react-router-dom';

let modules = [];

export default class CourseOverview extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      course: props.course
    }
  }

  async componentDidMount() {
    try {
      const modulesUrl = `http://localhost:8080/course/{this.props.course}/getModules`; 
      let modulesResponse = await fetch(modulesUrl);
      let modulesResponseJSON = await modulesResponse.json();
      modules = await modulesResponseJSON.courses;
    }
    catch(e) {
      modules[0] = "No modules found";
    }
  }

  render() {
    return (
      <div className = "courseOverview">
        if (modules[0] === "No modules found") {
          modules[0]
        }
        else {
          <ul>
            modules.forEach(function(element) {
              <li>
              <Link to = "/" module = {modules.module_id}> { modules.module_name } </Link>
              </li>
            });
          </ul>
        }
      </div>
    );
  }

}
