import React from 'react';

let test = "";
let questions = [];
let correctAnswers;
let result;

export default class Test extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      module: props.currentModule,
      test,
      questions,
      testTaken: false,
      result
    }
    this.getTestResult = this.getTestResult.bind(this);
  }

  async componentDidMount() {
    let testId;
    let qs;

    try {
      const testUrl = `http://localhost:8080/module/${this.state.module}/getModuleTest`;
      let testResponse = await fetch(testUrl);
      let testResponseJSON = await testResponse.json();
      testId = await testResponseJSON;

      const questionsUrl = `http://localhost:8080/test/${testId}/getQuestions`;
      let questionsResponse = await fetch(questionsUrl);
      let questionsResponseJSON = await questionsResponse.json();
      qs = await questionsResponseJSON;
    }
    catch(e) {
      testId = "";
      qs = "";
    }

    this.setState({
      test: testId,
      questions: qs
    });
  }

  getTestResult() {
    //number of radiobuttons named correct that are selected divided by question.answers.length = percentage to display in Test overview and store in database
    this.setState({
      testTaken: true
      //result: calculatedResult
    })
  }

  render() {
    let output = [];

    if (this.state.testTaken === true) {
      output.push(
        <div className = "TestResult">
          Test result will be displayed here.
        </div>
        //Your result is: {this.state.result}
      )
    }
    else {
      this.state.questions.forEach(function(question){
        output.push(
          <div>{question.question}</div>
        )
        question.answers.forEach(function(answer){
          output.push(
            <div><input type="radio" id="answer" name={`${question.question}`} value={`${question.answers.correct}`}/>
            <label>{answer.answer}</label></div>
          )
        })
      })
      output.push(<button onClick={this.getTestResult}>help me</button>)

    }

    return (
      <div className = "testDisplay">
        {output}
      </div>
    );
  }
}
