import React, {Component} from 'react';

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
                let yturl = "https://www.youtube.com/embed/";
                console.log(myJson);
                let parent = document.getElementById("videos");
                parent.innerHTML = "";
                for(let i =0; i < myJson.length; i++){
                let name = document.createElement("p");
                name.setAttribute("className","lessonTitle");
                name.innerHTML = myJson[i].name;
                let vid = document.createElement("iframe");
                let wholeurl = myJson[0].url;
                //splits to get id
                let arr = wholeurl.split("/");
                let a = arr[3];
                //splits to check if id still needs to be filtered
                arr = a.split("=");
                if(arr.length == 2){
                    yturl= yturl+arr[1];
                }
                else{
                    yturl= yturl+arr[0];
                }
                vid.setAttribute("src",yturl);
		        vid.style.width="560px";
		        vid.style.height="315px";
                parent.appendChild(vid);
                parent.appendChild(name);
                }
            });
    }

    
    render(){
        return(
            <div id="ytlink">
                <div id="videos">
                </div>
                <div id="linker">
                    <button name="9"  type="button" onClick={this.handleClick}> Section 9</button>
                    <button name="20"  type="button" onClick={this.handleClick}> Section 20</button>
                </div>

            </div>
        )
    }
}

export default Yt;