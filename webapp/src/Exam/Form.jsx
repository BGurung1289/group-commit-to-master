import React from "react";
import TestTitle from "./TestTitle";
import AddQuestions from "./AddQuestions";
import "./Exam.css";

class Form extends React.Component {

    constructor(props) {
        super(props);
        console.log(props.match)
        this.state = {
            Questions: [{
                addQuestion: "",
                moduleId: props.match.params.moduleId
            }]

        }
    }

    handleSubmit = (e) => {
        // e.preventDefault();
        const {moduleId} = this.state;
        const data = new FormData(e.target);
        let arrayList = [];
        for (let pair of data.entries()) {
            arrayList.push({title: pair[0], value: pair[1]});
            console.log(arrayList);
        }

        console.log("moduleid " + moduleId)
        data.append("moduleId", moduleId);

        fetch("http://localhost:8080/TestModel", {
            method: 'POST',
            body: JSON.stringify(arrayList)
        }).then(function () {
            document.location.href = "/trainerPage";
        });
    };


    addQuestion = (e) => {
        this.setState((prevState) => ({
            Questions: [...prevState.Questions, {addQuestion: ""}],
        }));
    }

    render() {
        let {Questions} = this.state;
        return (
            <div className = "form">
                <form id=" test-form" onSubmit={this.handleSubmit}>
                    <h2> Create a test </h2>
                    <p> Each question must have a minimal of one correct and one incorrect answer. You do not need to
                        have more than one question. </p>
                    <TestTitle/>
                    <br/>
                    <label> How many marks is this test out of? </label>
                    <div className = "form-group">
                     <input type="number" name="totalMarks" className="form-control" required/>
                    </div>
                    <label> Please enter any further description or information you would like alongside your test? </label>
                    <div className = "form-group"> 
                        <input type="text" name="testDescription" className="form-control" required/> 
                    </div>
                    <div className = "questionss">
                    <label htmlFor="Question">Question 1</label>
                    <div className="form-group"> 
                      <input type="text" name="QC1" className="form-control" required/> <br/>
                    </div>
                  
                    <label>Please enter the correct answer</label>
                    <div className="form-group"> 
                    <input type="text" name="A1a" className="form-control" placeholder="Answer..." required/> 
                    </div>
                    
                    <label>Please enter your incorrect answers</label>
                     <div className="form-group"> 
                    <input type="text" name="A1b" className="form-control" placeholder="Answer..." required/>
                    </div>
                     <div className="form-group"> 
                    <input type="text" name="A1b" className="form-control" placeholder="Answer..."/>
                    </div>
                    <div className="form-group">
                    <input type="text" name="A1b" className="form-control" placeholder="Answer..."/>
                        </div>
                    </div>
                     
                    <AddQuestions Questions={Questions}/>
               <div id = "leButtons">
                    <input id className = "btn btn-primary" type="submit" value="Submit"/>
                     <button id = "addQbtn" className = "btn btn-primary" onClick={this.addQuestion}>Add a new question</button>
                    </div>
                </form>
               
            </div>
        )
    }
}

export default Form;
