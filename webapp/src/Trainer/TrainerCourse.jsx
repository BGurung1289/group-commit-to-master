import React from "react";
import {Link} from "react-router-dom";
import {Popover} from "react-bootstrap";
import OverlayTrigger from "react-bootstrap/es/OverlayTrigger";
import Button from "react-bootstrap/es/Button";

export default class TrainerCourse extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            modules: null,
            courseId: props.match.params.courseId
        };
    }

    componentDidMount() {
        fetch("http://localhost:8080/module/trainerModules/" + this.state.courseId)
            .then(function (response) {
                return response.json()
            }).then(modules => {
            this.setState({modules})
        });
    }

    render() {
        console.log(this.state.modules)
        const {modules} = this.state;
        let temp;
        let pop;
        let addSectionUrl;

        return (
            <div className="w3-content">
                <div>
                    <h3>Add new module</h3>
                    <Link to='/addModule'>Add Module</Link>
                </div>
                <div>
                    <h3>Your Modules</h3>
                    <ul>
                        {modules ? (
                            modules.map(module => (
                                    temp = "/trainerModule/" + module.moduleId,
                                        addSectionUrl = "/addSection/" + module.moduleId,
                                        pop = (<Popover id="popover-positioned-right" title="Options">
                                            <Link to={temp} key={modules.moduleId}>Sections</Link>
                                            <br/>
                                            <Link to={addSectionUrl}>Add Section</Link>
                                        </Popover>),
                                        <li key={module.moduleId}><OverlayTrigger rootClose trigger="click"
                                                                                  placement="right"
                                                                                  key={module.moduleId} overlay={pop}>
                                            <Button>{module.moduleName}</Button>
                                        </OverlayTrigger></li>
                                )
                            )
                        ) : <h4>No Modules to display</h4>}
                    </ul>
                </div>
            </div>
        )
    }
}