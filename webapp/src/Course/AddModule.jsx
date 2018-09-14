import React from "react";

export default class AddModule extends React.Component {
    handleSubmit = (event) => {
        const data = new FormData(event.target);
        //TODO get course ID
        data.append("courseId", "15");

        fetch("http://localhost:8080/module/add", {
            method: 'POST',
            body: data
        });
    };


    render() {
        return (
            <div id="addModuleDiv" className="w3-card">
                <form id="addModuleForm" onSubmit={this.handleSubmit}>
                    <label>Module Name</label>
                    <input name="moduleName" required type="text" placeholder="Enter module name"/>
                    <br/>
                    <label>Module description</label>
                    <input name="moduleDescription" type="text" placeholder="Enter module description"/>
                    <br/>
                    <input type="submit" value="Add Module"/>
                </form>
            </div>
        )
    }
}