import React from 'react';
import Test from './Test';
import Section from './Section';


let moduleSections = [];

export default class Module extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      moduleSections,
      course: props.course,
      module: props.module,
      section: 0
    }

    this.updateSection = this.updateSection.bind(this);
  }

  async componentDidMount() {
    let sections;

    try {
      const moduleUrl = `http://localhost:8080/module/${this.state.module}/getSections`;
      let moduleResponse = await fetch(moduleUrl);
      let moduleResponseJSON = await moduleResponse.json();
      sections = await moduleResponseJSON;
    }
    catch(e) {
      sections = "";
    }

    this.setState({
      moduleSections: sections
    });
  }

  updateSection() {
    let currentSection = this.state.section;
    currentSection++;

    this.setState({
      section: currentSection
    });
  }

  render() {
    console.log("module: " + this.state.module);

    let output = [];

    if (this.state.moduleSections.length === 0 || this.state.moduleSections === undefined) {
      output.push(
        "Module not found."
      );
    }
    else if (this.state.section >= this.state.moduleSections.length) {
      output.push(
        <Test currentModule = {this.state.module} />
      );
    }
    else {
      output.push(
        <div className = "section">
          <Section sections = {this.state.moduleSections} currentSection = {this.state.section} />
          <button onClick={this.updateSection}>Next</button>
        </div>
      );
    }

    return (
      <div className="courseDisplay">
        {output}
      </div>
    );
  }

}
