import React from 'react';
import { Link } from 'react-router-dom';
import { Route } from 'react-router-dom';
import Module from './Module';

import { BrowserRouter as Router } from 'react-router-dom';

let modules;

export default class CourseOverview extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      course: props.course,
      modules
    }
  }

  async componentDidMount() {
    let currentModules;
    try {
      const modulesUrl = `http://localhost:8080/course/${this.state.course}/getModules`;
      let modulesResponse = await fetch(modulesUrl);
      let modulesResponseJSON = await modulesResponse.json();
      currentModules = await modulesResponseJSON;
    }
    catch(e) {
      currentModules = "";
    }
    this.setState({
      modules: currentModules
    });
  }

  render() {
    let output = [];

    (this.state.modules === undefined || this.state.modules.length < 1) ?
        output += "No modules found."
    :
      this.state.modules.forEach(module => {
        output.push(
          <li>
            <Link className = "courseLink" to = {`/course/${this.state.course}/module/${module.id}`} module = {module.id} >
              {module.name}
            </Link>
            <React.Fragment>
              <Route path = {`/course/${this.state.course}/module/${module.id}`} render = {(props) => <Module {...props} course = {this.state.course} module = {module.id} /> }  />
            </React.Fragment>
          </li>
        );
      })

    return (
      <Router>
      <div className = "courseOverview"><ul>
        {output}
      </ul></div>
      </Router>
    );
  }

}
