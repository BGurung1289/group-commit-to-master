import React from 'react';
import CourseOverview from './CourseOverview';
import Test from './Test';
import Section from './Section';
import {Link} from 'react-router-dom';

let moduleSections = [];

export default class Course extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      moduleSections,
      course: props.course,
      module: 0,
      section: 0
    }

    this.updateSection = this.updateSection.bind(this);
  }

  async componentDidMount(state) {
    try {
      const moduleUrl = `http://localhost:8080/module/${this.state.module}/getSections`;
      let moduleResponse = await fetch(moduleUrl);
      let moduleResponseJSON = await moduleResponse.json();
      moduleSections = await moduleResponseJSON.counter;
    }
    catch(e) {
      console.log("Module not found!");
    }
  }

  updateSection() {
    this.state.section++;
  }

  render() {
    return (
      <div className="courseDisplay">
        if (props.module === 0) {
          <div className="courseInformation">
            <CourseOverview course = {this.state.course} />
          </div>
        }
        else {
          <div className="courseInformation">
            (this.state.section > moduleSections.length) ?
            <Test currentModule = {this.state.module} /> :
            <Section currentSection = {moduleSections[this.state.section]} />
            <button onClick={this.updateSection}>Next</button>
          </div>
        }
      </div>
    );
  }
}
