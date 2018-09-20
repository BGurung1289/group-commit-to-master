import React from 'react';

const TestTitle = () => {
        return (
           <span id = "test-title" className = "title"> <label> Test Title </label>
                <div className = "form-group">
               <input type = "text" name="test_name" className = "form-control" required/>
                    </div>
    </span>
        );
    }
export default TestTitle;