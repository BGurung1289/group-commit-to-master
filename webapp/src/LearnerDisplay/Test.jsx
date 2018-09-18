import React from 'react';
import Question from './Question';

let questions = [];
let correctAnswers;

export default class Test extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      module: props.currentModule,
      test: 0,
      question: 0
    }
  }

  async componentDidMount(state) {
    try {
      const testUrl = `http://localhost:8080/module/${this.state.module}/getTest`;
      let testResponse = await fetch(testUrl);
      let testResponseJSON = await testResponse.json();
      this.state.test = await testResponseJSON.counter;

      const questionsUrl = `http://localhost:8080/test/${this.state.test}/getQuestions`;
      let questionsResponse = await fetch(questionsUrl);
      let questionsResponseJSON = await questionsResponse.json();
      questions = await questionsResponseJSON.counter;
    }
    catch(e) {
      console.log("No test found!");
    }
  }

  updateQuestion() {
    this.state.question++;
    //N[GOT OM EVENTHANTERING AV SVAREN?
    /*
    if (correct)
    */
    correctAnswers++;
  }

  render() {
    return (
      (this.state.question > questions.length) ?
      <div>Test overview will go here</div> :
      //<TestOverview  /> : //OBS! TestOvrview m[ste byggas. Den ska visa resultatet av testet och n'r usern skickas dit m]ste resultatet av testet sparas  DBn
      <Question currentQuestion = {this.state.question} />
      //<button onClick={this.updateQuestion}>Next</button>
    );
  }
}
