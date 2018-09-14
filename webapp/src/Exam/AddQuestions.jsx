import React from 'react';

const AddQuestions = (props) => {
return (
                    props.Questions.map((val, id) => {
                let addQuestionId = "addQuestion-${id}"
                return (
                    <div key={id}>
                        <label htmlFor={addQuestionId}> </label> 
                        <label htmlFor="Question">Question {id + 2} </label>
                <input type="text" id={"QC" + (id + 2)} required name="questionContent" className="question-content"/> <br />
                <label>Please enter the correct answer</label>
                <input type="text" id={"A" + (id + 2) + "a"} required name= "answerContent" className="answer" placeholder="Answer..." /> <br />
                <label>Please enter your incorrect answers</label> <br />
                <input type="text" required id={"A" + (id + 2) + "b"} name= "answerContent" className="answer" placeholder="Answer..." /> <br />
                <input type="text"  id={"A" + (id + 2) + "c"} name= "answerContent" className="answer" placeholder="Answer..." /> <br />
                <input type="text"  id={"A" + (id + 2) + "d"} name= "answerContent" className="answer" placeholder="Answer..." /> <br />
                </div>
                )
                })
                
);
}
            export default AddQuestions;
