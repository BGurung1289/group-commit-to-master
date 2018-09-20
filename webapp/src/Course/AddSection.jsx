import React from "react";

export default class AddSection extends React.Component {
    constructor(props) {
        super(props);
        console.log(props.match);
        this.state = {
            moduleId: props.match.params.moduleId
        };
    }

    handleSubmit = (event) => {
        const data = new FormData(event.target);
        const {moduleId} = this.state;

        data.append("moduleId", moduleId);

        // for (var pair of data.entries()) {
        //     console.log(pair[0] + ', ' + pair[1]);
        // }
        let youtubeurl = event.target.sectionYoutube.value;
        let youtubeTitle = event.target.youtubetitle.value;

        fetch("http://localhost:8080/section/add", {
            method: 'POST',
            body: data
        }).then(function (response) {
            return response.json();
        }).then(function (myJson) {
            let sectionid = JSON.parse(myJson).sectionid;
            let youtubeForm = new FormData();
            if (youtubeTitle.length == 0) return false;
            youtubeForm.append("videoUrl", youtubeurl);
            youtubeForm.append("sectionid", sectionid);
            youtubeForm.append("videoName", youtubeTitle);
            youtubeForm.append("isYoutube", 1);
            youtubeForm.append("trainerId", "1");

            fetch("http://localhost:8080/section/youtube", {
                method: 'POST',
                body: youtubeForm
            })
        }).then(function () {
            document.location.href = "/trainerPage";
        });
    };

    render() {
        return (
            <div id="addSectionDiv" className="w3-card w3-display-middle">
                <form id="addSectionForm" onSubmit={this.handleSubmit}>
                    <label>Section Name</label>
                    <input name="sectionName" required type="text" placeholder="Enter section name"/>
                    <br/>
                    <label>Section content</label>
                    <textarea required name="sectionContent" size="20" cols="40" type="text"
                              placeholder="Enter section content"/>
                    <br/>
                    <label>Youtube Video Title</label>
                    <input type="text" name="youtubetitle" placeholder="Enter Youtube video title"/>
                    <br/>
                    <label>Youtube link</label>
                    <input type="url" name="sectionYoutube" placeholder="Enter Youtube video URL"/>
                    <br/>
                    <input type="submit" value="Add Section"/>
                </form>
            </div>
        )
    }
}