import React from "react";

// export default class SearchResult extends React.Component{
//     constructor(props){
//         super(props);
//         console.log(props);
//         this.state = {
//             value: props
//         }
//     }
//
//     render(){
//         return(
//             <div>
//                 <p>{this.state.value[0]}</p>
//             </div>
//         )
//     }
// }

export default function SearchResult(props) {

    console.log("Props recieved " + props.display)

    console.log(JSON.stringify(props.display))
    return (
        <div>
            <p>{JSON.stringify(props.display)}</p>
        </div>
    );
}