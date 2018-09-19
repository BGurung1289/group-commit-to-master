import React from "react";

export default class CourseDetails extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            courseId: props.match.params.courseId,
            courseDetails: null,
            moduleDetails: null,
            sectionDetails: null,
            count : 0
        };
    }

    componentDidMount() {
        const {courseId, moduleDetails} = this.state;
        fetch("http://localhost:8080/course/" + courseId)
            .then(function (response) {
                return response.json();
            }).then(courseDetails => {
            this.setState({courseDetails: courseDetails.value})
        });

        fetch("http://localhost:8080/module/trainerModules/" + courseId)
            .then(function (response) {
                return response.json()
            }).then(moduleDetails => {
            // console.log(moduleDetails),
                this.setState({moduleDetails: moduleDetails})
        })
            // .then(function () {

                // fetch("http://localhost:8080/section/trainerSection/" + moduleDetails[0].moduleId)
                //     .then(function (response) {
                //         return response.json()
                //     }).then(sections => {
                //     this.setState({sections})
                // })
            // }
        // )


    }

    callToGetSection(){
        console.log("called me")

        // this.state.count++;
        // fetch("http://localhost:8080/section/trainerSection/" + this.state.moduleDetails[0].moduleId)
        //     .then(function (response) {
        //         return response.json()
        //     }).then(sections => {
        //         console.log(sections),
        //     this.setState({sections: [...sections]})
        // })
    }

    render() {
        const {courseDetails, moduleDetails, sectionDetails} = this.state;
        const course = Object.create(courseDetails);
        console.log(moduleDetails)
        if (moduleDetails != null && this.state.count < 1) this.callToGetSection();
        console.log(sectionDetails)

        return (
            <div className="w3-content">
                <div>
                    <h3>Course: {course.courseName}</h3>
                    <h4>Course Description:</h4>
                    <p>{course.courseDescription}</p>
                </div>
                <div>
                    <h3>Modules: </h3>
                    {moduleDetails ? (
                        moduleDetails.map(module => (
                            <div key={module.moduleId}>
                                <h4>{module.moduleName}</h4>
                                <p>{module.moduleDescription}</p>
                            </div>
                        ))) : <h5>No Modules</h5>}
                </div>
            </div>
        )
    }
}