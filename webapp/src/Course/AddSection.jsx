import React from "react";

export default class AddSection extends React.Component {
    handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.target);
        //TODO: change moduleID to match moduleID clicked
        data.append("moduleId", "3");

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
            youtubeForm.append("videoUrl", youtubeurl);
            youtubeForm.append("sectionid", sectionid);
            youtubeForm.append("videoName", youtubeTitle);
            youtubeForm.append("trainerId", "1");

            fetch("http://localhost:8080/section/youtube", {
                method: 'POST',
                body: youtubeForm
            });
        });
    };

    render() {
        return (
            <div id="addSectionDiv" className="w3-card">
                <form id="addSectionForm" onSubmit={this.handleSubmit}>
                    <label>Section Name</label>
                    <input name="sectionName" required type="text" placeholder="Enter section name"/>
                    <br/>
                    <label>Section content</label>
                    <textarea name="sectionContent" size="20" type="text" placeholder="Enter section content"/>
                    <br/>
                    <label>Upload video</label>
                    <input type="file"/>
                    <br/>
                    <label>Video Title</label>
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