import React from 'react';

const AddQuestions = (props) => {
return (
                    props.Questions.map((val, id) => {
                let addQuestionId = "addQuestion-${id}"
                return (
                    <div className = "questionss">
                    <div key={id}>
                        <label htmlFor={addQuestionId}> </label> 
                        <label htmlFor="Question">Question {id + 2} </label>
                        <div class_name= "form-group"> 
                <input type="text" className = "form-control" required name={"QC" + (id + 2)} /> 
                            </div>
                <label>Please enter the correct answer</label>
                        
                        <div className="form-group"> 
                         <input type="text" className= "form-control" required name= {"A" + (id + 2) + "a"} placeholder="Answer..." /> 
                        </div>
               
                <label>Please enter your incorrect answers</label> <br />
                        <div className= "form-group" >
                        <input type="text" className = "form-control" required name= {"A" + (id + 2) + "b"} placeholder="Answer..." />
                        </div>
                        
                 <div className="form-group"> 
                         <input type="text" name= {"A" + (id + 2) + "b"} className="form-control" placeholder="Answer..." />
                        </div>
                <div className="form-group">
                        <input type="text" className="form-control" name= {"A" + (id + 2) + "b"} placeholder="Answer..." /> 
                        </div>
                </div>
                    </div>
            )
        })

    );
}
export default AddQuestions;
