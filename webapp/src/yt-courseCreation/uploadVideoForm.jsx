import React from 'react';

export default class UploadVideoForm extends React.Component {
    constructor(props) {
        super(props);
        console.log(props.match.params.sectionId)
        this.state = {
            sectionId: props.match.params.sectionId
        };
    }

    uploadVideo = (event) => {
        // event.preventDefault();
        const {sectionId} = this.state;
        console.log(event.target.video_file.value.split('.')[1]);
        if (event.target.video_file.value.split('.')[1] === 'mp4' || event.target.video_file.value.split('.')[1] === 'mov' || event.target.video_file.value.split('.')[1] === 'avi') {
            let url = `https://api.cloudinary.com/v1_1/qacloudinary/upload`;
            let xhr = new XMLHttpRequest();
            let fd = new FormData();
            xhr.open('POST', url, true);
            xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
            fd.append('api_key', '871646615279882');
            fd.append('upload_preset', 'oqetlguy');
            fd.append('file', event.target.video_file.files[0]);
            xhr.send(fd);
            const videoInfo = new FormData();
            let beginignFileName = event.target.video_file.value.split('.')[0];
            let startIndex = (beginignFileName.indexOf('\\') >= 0 ?
                beginignFileName.lastIndexOf('\\') :
                beginignFileName.indexOf('/'));
            let filename = beginignFileName.substring(startIndex);
            if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
                filename = filename.substring(1);
            }
            videoInfo.append('videoName', event.target.video_name.value);
            videoInfo.append('videoUrl', filename);
            videoInfo.append('isYoutube', 0);
            videoInfo.append('trainerId', '1');
            videoInfo.append('sectionid', sectionId);
            fetch('http://localhost:8080/section/youtube', {
                method: 'POST',
                body: videoInfo
            });
        }
        else {
            document.getElementById("alert_not_video").innerHTML = "Only mp4, avi or mov files allowed";
        }
    };

    render() {
        return (
            <div className="video upload form" id="dropbox">
                <h3>Upload Video </h3>
                <form onSubmit={this.uploadVideo}>
                    <input name="video_file" type="file" accept="video/*"/><br/>
                    <input name="video_name" type="text" placeholder="Video name"/><br/>
                    <input type="submit" value="Upload"/>
                </form>
                <div id="alert_not_video">
                </div>
            </div>
        )
    }
}