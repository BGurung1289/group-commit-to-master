import React from "react";
import "../css/video.css";
export default class UploadVideoForm extends React.Component {
    uploadVideo = (event) => {
        event.preventDefault();
        if (event.target.video_file.value.split(".")[1] === "mp4" || event.target.video_file.value.split(".")[1] === "mov" || event.target.video_file.value.split(".")[1] === "avi") {
            var url = "https://api.cloudinary.com/v1_1/qacloudinary/upload";
            var xhr = new XMLHttpRequest();
            var fd = new FormData();
            xhr.open("POST", url, true);
            xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
            fd.append("api_key", "871646615279882");
            fd.append("upload_preset", "oqetlguy");
            fd.append("file", event.target.video_file.files[0]);
            xhr.send(fd);
            const videoInfo = new FormData();
            var beginignFileName = event.target.video_file.value.split(".")[0];
            var startIndex = (beginignFileName.indexOf("\\") >= 0 ?
                beginignFileName.lastIndexOf("\\") :
                beginignFileName.indexOf("/"));
            var filename = beginignFileName.substring(startIndex);
            if (filename.indexOf("\\") === 0 || filename.indexOf("/") === 0) {
                filename = filename.substring(1);
            }
            videoInfo.append("videoName", event.target.video_name.value);
            videoInfo.append("videoUrl", filename);
            videoInfo.append("isYoutube", 0);
            videoInfo.append("trainerId", "1");
            videoInfo.append("sectionid", "1");
            fetch("http://localhost:8080/section/youtube", {
                method: "POST",
                body: videoInfo
            });
        }
        else {
            document.getElementById("alert_not_video").innerHTML = "Only mp4, avi or mov files allowed";
        }
    }

    render() {
        return (
            <div className="video upload form" id="regForm">
                <form onSubmit={this.uploadVideo}>
                    <div className="form-group">
                        <input className="form-control" name="video_file" type="file" accept="video/*"/><br/>
                    </div>
                    <div className="form-group">
                        <input className="form-control-file" name="video_name" type="text" placeholder="Video name"/><br/>
                    </div>
                    <input type="submit" value="Upload"/>
                </form>
                <div id="alert_not_video">
                </div>
            </div>
        );
    }
}