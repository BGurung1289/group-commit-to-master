import React from "react";
import {Link} from "react-router-dom";
import {Popover} from "react-bootstrap";
import OverlayTrigger from "react-bootstrap/es/OverlayTrigger";
import Button from "react-bootstrap/es/Button";

export default class TrainerModule extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            moduleId: props.match.params.moduleId,
            sections: null
        };
    }

    componentDidMount() {
        fetch("http://localhost:8080/section/trainerSection/" + this.state.moduleId)
            .then(function (response) {
                return response.json()
            }).then(sections => {
            this.setState({sections})
        });
    }

    render() {
        const {sections} = this.state;
        let pop;
        return (
            <div className="w3-content">
                <div>
                    <h3>Add new section</h3>
                    <Link to='/addSection'>Add Section</Link>
                </div>

                <div>
                    <h3>Module sections</h3>
                    <ul>
                        {sections ? (
                            sections.map(section => (
                                        pop = (<Popover id="popover-positioned-right" title="Options">
                                            <Link to={"/uploadVideo/"+section.sectionId}>Add Video to Cloudinary</Link>
                                            <br/>
                                            <Link to={"/addExam/"+section.sectionId}>View Section</Link>
                                        </Popover>),
                                        <li key={section.sectionId}><OverlayTrigger rootClose trigger="click"
                                                                                  placement="right"
                                                                                  key={section.sectionId} overlay={pop}>
                                            <Button>{section.sectionName}</Button>
                                        </OverlayTrigger></li>
                                )
                            )
                        ) : <h4>No Sections to display</h4>}
                    </ul>


                    {/*<ul>*/}
                        {/*{sections ? (*/}
                            {/*sections.map(section => (*/}
                                    {/*temp = "/trainerModule/" + section.sectionId,*/}
                                        {/*<li key={section.sectionId}><Link to={temp} key={section.sectionId}>*/}
                                            {/*{section.sectionName}*/}
                                        {/*</Link></li>*/}
                                {/*)*/}
                            {/*)*/}
                        {/*) : <h4>No modules to display</h4>}*/}
                    {/*</ul>*/}
                </div>
            </div>
        )
    }
}