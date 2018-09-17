import React from 'react';

const TestTitle = () => {
        return (
           <span id = "test-title" className = "title"> <label> Test Title </label>
               <input type = "text" name="test_name" className = "enter-test-title" required/>
    </span>
        );
    }
export default TestTitle;