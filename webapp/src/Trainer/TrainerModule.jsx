import React from "react";
import {Link} from "react-router-dom";

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
        let temp;
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
                                    temp = "/trainerModule/" + section.sectionId,
                                        <li key={section.sectionId}><Link to={temp} key={section.sectionId}>
                                            {section.sectionName}
                                        </Link></li>
                                )
                            )
                        ) : <h4>No modules to display</h4>}
                    </ul>
                </div>
            </div>
        )
    }
}