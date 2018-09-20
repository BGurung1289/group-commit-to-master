import React from "react";
import {Video, Transformation} from "cloudinary-react";

export default class DisplayVideo extends React.Component{
constructor(props){
    super(props);
    this.state={id:props.id};
}
render(){
    return(
        <div>
            <div id="video_display">
            <Video cloudName="qacloudinary" publicId={this.state.id} controls={true} >
                <Transformation height="200" width="350"crop="scale" />
            </Video>
            </div>
            <div id="video_name">
                <h1>{this.state.id}</h1>
            </div>
        </div>
    );
}
}