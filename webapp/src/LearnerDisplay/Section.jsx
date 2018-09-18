import React from 'react';

let section;

export default class Section extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      section: props.currentSection
    }
  }

  async componentDidMount() {
  try {
      const sectionUrl = `http://localhost:8080/section/${this.state.section}`; 
      let response = await fetch(sectionUrl);
      let responseJSON = await response.json();
      section = await responseJSON.courses;
    }
    catch(e) {
      section = "Section not found";
    }
  }

  render() {
    return (
      <div className = "sectionDisplay">
        if (section === "Section not found") {
          <div className = "errorMessage">
            { section }
          </div>
        }
        else {
          <div className = "sectionInfo">
            <h3> { section.section_name } </h3>
            { section.section_content }
          </div>
        }
      </div>
    );
  }
}
