import React from 'react';
import Question from './Question';

let module;
let test = "";
let questions = [];
let currentQuestion;
let correctAnswers;


export default class Test extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      module: props.currentModule,
      test,
      questions,
      currentQuestion
    }
    console.log("TEST MODULE PUSH" + this.state.module);
  }

  async componentDidMount() {
    let testId;
    let qs;

    try {
      const testUrl = `http://localhost:8080/module/${this.state.module}/getModuleTest`;
      let testResponse = await fetch(testUrl);
      let testResponseJSON = await testResponse.json();
      testId = await testResponseJSON;
    }
    catch(e) {
      testId = "";
      console.log("No test found!" + this.state.module);
    }

    try {
      const questionsUrl = `http://localhost:8080/test/${this.state.test}/getQuestions`;
      let questionsResponse = await fetch(questionsUrl);
      let questionsResponseJSON = await questionsResponse.json();
      qs = await questionsResponseJSON;
    }
    catch(e) {
      qs = "";
      console.log("No Q's found!" + this.state.test);
    }

    this.setState({
      test: testId,
      questions: qs
    });
  }

  updateQuestion() {
    this.state.currentQuestion++;
    //N[GOT OM EVENTHANTERING AV SVAREN?
    /*
    if (correct)
    */
    correctAnswers++;
  }

  render() {
    let output = [];

    (this.state.currentQuestion >= questions.length) ?
    output.push(
      <div>Test overview will go here</div>
    )
    //OBS! TestOvrview ska visa resultatet av testet och n'r usern skickas dit m]ste resultatet av testet sparas  DBn???
    :
    output.push(
      <Question currentQuestion = {this.state.currentQuestion} />
    )


    return (
      <div className = "testDisplay">
        {output}
      </div>
    );
  }
}
