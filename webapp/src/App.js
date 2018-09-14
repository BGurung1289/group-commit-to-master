import React, { Component } from 'react';

import './App.css';
import AddCourse from "./Course/AddCourse";
import AddModule from "./Course/AddModule";
import AddSection from "./Course/AddSection";

class App extends Component {
  render() {
    return (
      <div className="App w3-container w3-content w3-display-middle">
          <AddCourse/>
          <br/>
          <AddModule/>
          <br/>
          <AddSection/>
        {/*<header className="App-header">*/}
          {/*<img src={logo} className="App-logo" alt="logo" />*/}
          {/*<h1 className="App-title">Welcome to React</h1>*/}
        {/*</header>*/}
        {/*<p className="App-intro">*/}
          {/*To get started, edit <code>src/App.js</code> and save to reload.*/}
        {/*</p>*/}
      </div>
    );
  }
}

export default App;
