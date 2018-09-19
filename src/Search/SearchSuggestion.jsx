import React from "react";

export default function SearchSuggestion(props) {
    const choices = props.result.map(value => {
        <li key={value.id}>
            {value.name}
        </li>
    })
    return <ul>{choices}</ul>
}

