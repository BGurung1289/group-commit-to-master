import React from "react";
import TestTitle from "./TestTitle";
import AddQuestions from "./AddQuestions";

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
            <div>
                <form id=" test-form" onSubmit={this.handleSubmit}>
                    <h3> Create a test </h3>
                    <p> Each question must have a minimal of one correct and one incorrect answer. You do not need to
                        have more than one question. </p>
                    <TestTitle/>
                    <br/>
                    <label> How many marks is this test out of? </label>
                    <input type="number" name="totalMarks" className="marks-for-test" required/>
                    <br/>

                    <label> Please enter any further description or information you would like alongside your test?
                    </label> <input type="text" name="testDescription" className="test-description" required/> <br/>


                    <label htmlFor="Question">Question 1</label>
                    <input type="text" name="QC1" className="question-content" required/> <br/>
                    <label>Please enter the correct answer</label>
                    <input type="text" name="A1a" className="answer" placeholder="Answer..." required/> <br/>
                    <label>Please enter your incorrect answers</label> <br/>
                    <input type="text" name="A1b" className="answer" placeholder="Answer..." required/> <br/>
                    <input type="text" name="A1b" className="answer" placeholder="Answer..."/> <br/>
                    <input type="text" name="A1b" className="answer" placeholder="Answer..."/> <br/>


                    <AddQuestions Questions={Questions}/>

                    <input type="submit" value="Submit"/>
                </form>
                <button onClick={this.addQuestion}>Add a new question</button>
            </div>
        )
    }
}

export default Form;
