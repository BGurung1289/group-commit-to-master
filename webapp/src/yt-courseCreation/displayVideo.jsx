import React from 'react';
import {Video, Transformation} from 'cloudinary-react';

export default class DisplayVideo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            id: props.id,
            name: props.name
        }
    }

    render() {
        return (
            <div>
                <br/>
                <div id="video_name">
                    <hr>
                    </hr>
                    <h4>{this.state.name}</h4>
                </div>
                <div className="video_display">
                    <Video cloudName="qacloudinary" publicId={this.state.id} height="300" width="450" controls={true}>
                        {/*<Transformation height="200" width="350" crop="scale"/>*/}
                    </Video>
                </div>
            </div>
        )
    }
}