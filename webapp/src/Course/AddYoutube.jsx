import React from "react";

export default class AddYoutube extends React.Component {
    constructor(props) {
        super(props);
        console.log(props.match);
        this.state = {
            sectionId: props.match.params.sectionId
        };
    }

    handleSubmit = (event) => {
        const data = new FormData(event.target);
        const {sectionId} = this.state;

        data.append("isYoutube", 1);
        data.append("trainerId", "1");
        data.append("thisIsYoutube", "1");
        data.append("sectionid", sectionId);

        fetch("http://localhost:8080/section/youtube", {
            method: 'POST',
            body: data
        }).then(function () {
            document.location.href = "/trainerPage";
        })
    };

    render() {
        return (
            <div className="w3-content">
                <h3>Add Youtube Video </h3>
                <form onSubmit={this.handleSubmit}>
                    <label>Youtube Video Title</label>
                    <input type="text" name="videoName" placeholder="Enter Youtube video title"/>
                    <br/>
                    <label>Youtube link</label>
                    <input type="url" name="videoUrl" placeholder="Enter Youtube video URL"/>
                    <br/>
                    <input type="submit" value="Add Youtube"/>
                </form>
            </div>
        )
    }
}