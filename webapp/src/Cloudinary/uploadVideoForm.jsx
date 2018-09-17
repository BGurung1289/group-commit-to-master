import React from 'react';
import {Image, Video, Transformation, CloudinaryContext} from 'cloudinary-react';
export default class UploadVideoForm extends React.Component {

    constructor(){
        super();
     /*   Cloudinary.config({
            cloud_name:'qacloudinary',
            api_key:'871646615279882',
            api_secret:'ASEIqqe3p_DAr8El3vjx1nlwKrY'
        });*/

    }
    uploadVideo=(event)=>{
        let cloudName='qacloudinary';
        var url = `https://api.cloudinary.com/v1_1/${cloudName}/upload`;

    }
    render() {
        return (
            <div className="video upload form">
                <form onSubmit={uploadVideo}>
                    <input name="video_file" type="file" /><br/>
                    <input type="submit" value="Upload" />
                </form>
            </div>
        )
    }
}