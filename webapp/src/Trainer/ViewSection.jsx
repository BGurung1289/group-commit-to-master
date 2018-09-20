import React from "react";
import Yt from "../yt-courseCreation/yt";


export default class ViewSection extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            sectionId: props.match.params.sectionId,
            section: null
        }
    }

    componentDidMount() {
        const {sectionId} = this.state;
        fetch("http://localhost:8080/section/" + sectionId)
            .then(function (response) {
                return response.json()
            }).then(section => {
            this.setState({section})
        })
    }

    render() {
        const {section, sectionId} = this.state;

        return (
            <div className="w3-content">
                {section ?
                    <div>
                        <h2>{section.sectionName}</h2>
                        <p>{section.sectionContent}</p>
                    </div>
                    : "No section data to show"}
                <Yt sectionId={sectionId} />
            </div>
        )
    }

}