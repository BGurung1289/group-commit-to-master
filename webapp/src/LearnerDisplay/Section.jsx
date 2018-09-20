import React from 'react';

let sections = [];
let currentSection;
let content = "";

export default class Section extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      sections: props.sections,
      currentSection: props.currentSection,
      content
    }
  }

  async componentDidMount() {
    let sectionContent;

    let current;
    this.state.sections.forEach(section => {
      if (section.orderInModule === this.state.currentSection) {
        current = section.id;
      }
    });

  try {
      const sectionUrl = `http://localhost:8080/section/${current}`;
      let sectionResponse = await fetch(sectionUrl);
      let sectionResponseJSON = await sectionResponse.json();
      sectionContent = await sectionResponseJSON;
    }
    catch(e) {
      sectionContent = "";
    }

    this.setState({
      content: sectionContent
    });
  }

  render() {
    let output = [];

    if (this.state.section === "" || this.state.section === null) {
      output.push(
        <div className = "errorMessage">
          {currentSection}
        </div>)
      ;
    }
    else {
      output.push(
        <div className = "sectionInfo">
          <h3>{this.state.content.name}</h3>
          {this.state.content.content}
        </div>
      );
    }

    return (
      <div className = "sectionDisplay">
        {output}
      </div>
    );
  }
}
