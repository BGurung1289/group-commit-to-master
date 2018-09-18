import React, {Component} from 'react';
import {BrowserRouter, Route} from 'react-router-dom';

import './App.css';
import Header from "./main/Header";
import Footer from "./main/Footers";
import Home from "./main/Home";
import Login from "./login/login";
import Registration from "./registration/Registration";

class App extends Component {
    render() {
        return (
            <BrowserRouter>
                <React.Fragment>
                    <Header/>
                    <main className="w3-content">
                        <Route exact path="/" component={Home}/>
                        <Route path="/login" component={Login}/>
                        <Route path="/register" component={Registration}/>
                    </main>
                    <Footer/>
                </React.Fragment>
            </BrowserRouter>
        );
    }
}

export default App;
{/*//  <AddCourse/>*/
}
{/*//          <br/>*/
}
{/*//          <AddModule/>*/
}
{/*//          <br/>*/
}
{/*//          <AddSection/>*/
}
{/*<div className="App w3-container w3-content w3-display-middle">*/
}
{/*<Form />*/
}
{/*</div>*/
}
//        {/*<header className="App-header">*/}
//          {/*<img src={logo} className="App-logo" alt="logo" />*/}
//          {/*<h1 className="App-title">Welcome to React</h1>*/}
//        {/*</header>*/}
//        {/*<p className="App-intro">*/}
//          {/*To get started, edit <code>src/App.js</code> and save to reload.*/}
//        {/*</p>*/}