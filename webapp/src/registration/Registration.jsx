 import React from 'react';

export default class Registration extends React.Component{

    handleSubmit = (event)=> {
        const data = new FormData(event.target);
        console.log(data);
        /*MAPPING URL NEEDS TO BE PUT HERE*/
        fetch ("",{
            method:'POST', body: data
        });
    }
    render(){
        return (
            <div className="register_page">
                <h1>Time to Register</h1>
                <div className="register_form">
                    <form onSubmit={this.handleSubmit}>
                        <input type="text" required placeholder="Email" /><br/>
                        <input type="text" required placeholder="First Name" /><br/>
                        <input type="text" required placeholder="Last Name" /><br/>
                        <input type="password" required placeholder="Password" /><br />
                        <input name="" type="submit" value="Submit" />
                    </form>
                </div>
            </div>
        )
    }
}