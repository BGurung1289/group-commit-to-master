import React, { Component } from "react";
import "./App.css";
import AddCourse from "./Course/AddCourse";
import AddModule from "./Course/AddModule";
import AddSection from "./Course/AddSection";
import Search from "./Search/Search";
import {
  ReactiveBase,
  DataSearch,
  SelectedFilters,
  ResultCard
} from "@appbaseio/reactivesearch";



class App extends Component {
  render() {
    return (
	
	<ReactiveBase	
	//credentials from dataset at https://dashboard.appbase.io/login/apps
	//login with github account lmsgroupproject
        app=""
        credentials=""
      />
        <div className="navbar">
		</div>
		<div className="logo">
            LMS search
          </div>
      <div className="App w3-container w3-content">
          <Search/>
          {/*<AddCourse/>*/}
          {/*<br/>*/}
          {/*<AddModule/>*/}
          {/*<br/>*/}
          {/*<AddSection/>*/}
        {/*<header className="App-header">*/}
          {/*<img src={logo} className="App-logo" alt="logo" />*/}
          {/*<h1 className="App-title">Welcome to React</h1>*/}
        {/*</header>*/}
        {/*<p className="App-intro">*/}
          {/*To get started, edit <code>src/App.js</code> and save to reload.*/}
        {/*</p>*/}
      </div>
	  </ReactiveBase>	  
    );
  }
}

export default App;
