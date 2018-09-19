import React from "react";
import SearchResult from "./SearchResult";

export default class Search extends React.Component {

    state = {
        courses: '',
        modules: [],
        section: [],
        results: ''
    };

    courseValues;
    moduleValues;
    sectionValues;
    async componentDidMount() {
        // console.log("Search loaded");
        this.courseValues = await fetch("http://localhost:8080/course/searchCourse")
            .then(function (response) {
                return response.json()
            });
        this.moduleValues = await fetch("http://localhost:8080/module/searchModule")
            .then(function (response) {
                return response.json()
            });
        this.sectionValues = await fetch("http://localhost:8080/section/searchSection")
            .then(function (response) {
                return response.json()
            });

        // console.log(this.courseValues);
        this.courseValues.forEach(function (each) {
            each.Category = "course"
        });
        this.moduleValues.forEach(function (each) {
            each.Category = "module"
        });
        this.sectionValues.forEach(function (each) {
            each.Category = "section"
        });


        this.setState({courses: this.courseValues})
        this.courseValues = ''
    }

    onSearch = (event) => {
        console.log(event.target.value)
        console.log("Compare with value and each results")
    };

    render() {
        return (
            <div id="searchBarDiv" className="w3-display-top">
                <form>
                    <input name="searchValue" onChange={this.onSearch}
                           placeholder="Search Course or Module or Section name"/>
                    {/*<SearchSuggestion/>*/}
                </form>
                {/*{this.courseValues == 'undefined' ?*/}
                <SearchResult display={this.state.courses}/>
                    {/*:false}*/}
            </div>
        )
    }
}