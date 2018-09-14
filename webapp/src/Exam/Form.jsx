import React from "react";
import TestTitle from "./TestTitle";
import AddQuestions from "./AddQuestions";

class Form extends React.Component {
    //....State holding an array
    constructor(props) {
        super(props);
        this.state = {
            Questions: [{
                addQuestion: ""
            }]
             
        }
    }

     handleSubmit = (e) => { 
        e.preventDefault();
    const data = new FormData(e.target);
    let arrayList = [];
    for (let pair of data.entries()) {
        arrayList.push({title: pair[0] , value: pair[1]});
        console.log(arrayList);    
    }

data.append("moduleId", "5"); // this will need to be an ID given by JAVA.

    fetch("http://localhost:8080/api/TestQuestionModel", {
        method: 'POST',
        body: JSON.stringify(arrayList)
});
     };
    

    addQuestion = (e) => {
        this.setState((prevState) => ({
            Questions: [...prevState.Questions, { addQuestion: "" }],
        }));
    }

   // in the ID's, QC stands for question content, A stands for answer, the number corresponds to the Question Number and a/b/c/d are the different answers.
 // a is the correct answer and the other letters are incorrect.
    render() {
        let {Questions } = this.state;
        return (
<div>
            <form id=" test-form" onSubmit={this.handleSubmit} >
                <h3> Create a test </h3> 
                <p> Each question must have a minimal of one correct and one incorrect answer. You do not need to have more than one question. </p>
                <TestTitle />
                <br/>
                
                <label htmlFor="Question">Question 1</label>
                <input type="text" id= {"QC" + 1} name={"questionContent"}  className="question-content" required/> <br />
                <label>Please enter the correct answer</label>
                <input type="text" id={"A" + 1 + "a"} name= "answerContent" className="answer" placeholder="Answer..." required /> <br />
                <label>Please enter your incorrect answers</label> <br />
                <input type="text" id ={"A" + 1 + "b"} name= "answerContent" className="answer" placeholder="Answer..." required/> <br />
                <input type="text" id ={"A" + 1 + "c"} name= "answerContent" className="answer" placeholder="Answer..." /> <br />
                <input type="text" id ={"A" + 1 + "d"} name= "answerContent" className="answer" placeholder="Answer..." /> <br />
                <label> How many marks is this test out of? </label>
                <input type="number" name = "totalMarks" className= "marks-for-test"/>
                
               <AddQuestions Questions={Questions}/> 

                 
                <input type="submit" value="Submit" />
            </form>
            <button onClick={this.addQuestion} >Add a new question</button>
            </div>
        )
    }
}
export default Form;
