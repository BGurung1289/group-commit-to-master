import React, {Component} from 'react';
import {BrowserRouter, Route} from 'react-router-dom';

import './App.css';
import Header from "./main/Header";
import Footer from "./main/Footers";
import Home from "./main/Home";
import Login from "./login/login";
import Registration from "./registration/Registration";
import Courses from "./LearnerDisplay/Courses";
import {TrainerUserPage} from "./Trainer/TrainerUserPage";
import AddCourse from "./Course/AddCourse";
import ShowCourseDetails from "./LearnerDisplay/ShowCourseDetails";

class App extends Component {
    state = {
        courses: ''
    };

    async componentDidMount(){
        console.log("MOUNTED")
        this.courseValues = await fetch("http://localhost:8080/course/searchCourse")
            .then(function (response) {
                return response.json()
            });

        this.setState({courses: this.courseValues})
    }
    render() {
        return (
            <BrowserRouter>
                <React.Fragment>
                    <Header/>
                    <main className="w3-content">
                        <Route exact path="/" component={Home}/>
                        <Route path="/login" component={Login}/>
                        <Route path="/register" component={Registration}/>
                        <Route path="/courses" component={Courses}/>
                        <Route path="/trainerPage" component={TrainerUserPage}/>
                        <Route path="/addCourse" component={AddCourse}/>
                        <Route path="/showcourse/:courseId" component={ShowCourseDetails}/>
                    </main>
                    <Footer/>
                </React.Fragment>
            </BrowserRouter>
        );
    }
}

export default App;