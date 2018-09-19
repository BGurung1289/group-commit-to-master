import React, {Component} from 'react';
import DisplayVideo from './displayVideo'; 
import ReactDOM from 'react-dom';
import DisplayYtVideo from './displayYtVideo';

class Yt extends Component{
    constructor(){
        super();
        this.state={
            vId:""
        }
    }

    handleClick = event =>{
    this.state.vId = event.target.name;    
    fetch("http://localhost:8080//getVideo/" + this.state.vId)
    .then(
            function (response) {
                return response.json();
            })
            .then(function (myJson) {
                console.log(myJson);
                let yturl = "https://www.youtube.com/embed/";
                let parent = document.getElementById("videos");
                for(let i =0; i < myJson.length; i++){
                    if(myJson[i].isYoutube===true){
                        // parent.innerHTML = "";
                        // let name = document.createElement("p");
                        // name.setAttribute("className","lessonTitle");
                        // name.innerHTML = myJson[i].name;
                        // let vid = document.createElement("iframe");
                        // let wholeurl = myJson[i].url;
                        // //splits to get id
                        // let arr = wholeurl.split("/");
                        // let a = arr[3];
                        // //splits to check if id still needs to be filtered
                        // arr = a.split("=");
                        // if(arr.length == 2){
                        //     parent.innerHTML = "";
                        //     yturl= yturl+arr[1];
                        // }
                        // else{
                        //     parent.innerHTML = "";
                        //     yturl= yturl+arr[0];
                        // }
                        // vid.setAttribute("src",yturl);
                        // vid.style.width="560px";
                        // vid.style.height="315px";
                        // parent.appendChild(vid);
                        // parent.appendChild(name);
                        ReactDOM.render(<DisplayYtVideo name={myJson[i].name} url={myJson[i].url}/>, document.getElementById("videos"));
                    }
                    else{
                        //<DisplayVideo id={myJson[i].url} />
                        // document.getElementById("videos").dangerouslySetHtml={__html:DisplayVideo};
                        ReactDOM.render(<DisplayVideo id={myJson[i].url} />, document.getElementById("videos"));
                    }
                }
            });
    }
    render(){
        return(
            <div id="ytlink">
                <div id="videos">
                </div>
                <div id="linker">
                    <button name="12"  type="button" onClick={this.handleClick}> Section 12</button>
                    <button name="9"  type="button" onClick={this.handleClick}> Section 9</button>
                </div>
            </div>
        )
    }
}

export default Yt;