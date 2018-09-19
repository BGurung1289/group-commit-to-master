import React from 'react'


export default class DisplayYtVideo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: props.name,
            url: props.url
        }
    }

    bosh = () => {
        let yturl = "https://www.youtube.com/embed/";
        let wholeurl = this.state.url;
        //splits to get id
        let arr = wholeurl.split("/");
        let a = arr[3];
        //splits to check if id still needs to be filtered
        arr = a.split("=");
        if (arr.length == 2) {
            yturl = yturl + arr[1];
        }
        else {
            yturl = yturl + arr[0];
        }
        this.state.url = yturl;
    }

    render() {
        this.bosh();
        return (
            <div>
                <div id="video_display">
                    <iframe src={this.state.url} width="350px" height="200px"> </iframe>
                </div>
                <div id="video_name">
                    <h1>{this.state.name}</h1>
                </div>
            </div>
        )
    }
}