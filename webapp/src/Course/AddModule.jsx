import React from "react";

export default class AddModule extends React.Component {
    constructor(props) {
        super(props);
        console.log(props.match)
        this.state = {
            courseId: props.match.params.courseId
        };
        console.log(this.state.courseId)
    }

    handleSubmit = (event) => {
        const data = new FormData(event.target);
        const {courseId} = this.state;
        //TODO get course ID
        data.append("courseId", courseId);

        fetch("http://localhost:8080/module/add", {
            method: 'POST',
            body: data
        }).then(function () {
                document.location.href = "/trainerPage";
            }
        );
    };


    render() {
        return (
            <div id="addModuleDiv" className="w3-display-middle w3-card">
                <form id="addModuleForm" onSubmit={this.handleSubmit}>
                    <label>Module Name</label>
                    <input name="moduleName" required type="text" placeholder="Enter module name"/>
                    <br/>
                    <label>Module description</label>
                    <textarea name="moduleDescription" rows="10" cols="70" type="text"
                              placeholder="Enter module description"/>
                    <br/>
                    <input type="submit" value="Add Module"/>
                </form>
            </div>
        )
    }
}