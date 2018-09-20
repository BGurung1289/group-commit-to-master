import React, {Component} from 'react';
import DisplayVideo from './displayVideo';
import ReactDOM from 'react-dom';
import DisplayYtVideo from './displayYtVideo';

class Yt extends Component {
    constructor(props) {
        super(props);
        console.log(props.sectionId)
        this.state = {
            vId: props.sectionId
        }
    }

    componentDidMount() {
        fetch("http://localhost:8080/getVideo/" + this.state.vId)
            .then(
                function (response) {
                    return response.json();
                })
            .then(function (myJson) {
                console.log(myJson);
                for (let i = 0; i < myJson.length; i++) {
                    console.log(myJson[i])
                    if (myJson[i].isYoutube === true) {
                    ReactDOM.render(<DisplayYtVideo name={myJson[i].name}
                                                    url={myJson[i].url}/>, document.getElementById("videos"));
                    }
                    else {
                    ReactDOM.render(<DisplayVideo name={myJson[i].name}
                                                  id={myJson[i].url}/>, document.getElementById("video"));
                    }
                }
            });
    }

    render() {
        return (
            <div id="ytlink">
                <div id="videos">
                </div>
                <div id="video">
                </div>
            </div>
        )
    }
}

export default Yt;