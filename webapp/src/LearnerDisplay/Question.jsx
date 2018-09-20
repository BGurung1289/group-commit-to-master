import React from 'react';

let answers = [];

export default class Question extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      question: props.currentQuestion,
      answers
    }
  }

  async componentDidMount(state) {
    try {
      const questionUrl = `http://localhost:8080/module/${this.state.module}/getQuestion`;
      let questionResponse = await fetch(questionUrl);
      let questionResponseJSON = await questionResponse.json();
      this.state.question = await questionResponseJSON.counter;

      const answersUrl = `http://localhost:8080/test/${this.state.test}/getAnswers`;
      let answersResponse = await fetch(answersUrl);
      let answersResponseJSON = await answersResponse.json();
      answers = await answersResponseJSON.counter;
    }
    catch(e) {
      console.log("No test founddd!");
    }
  }

  // I DENNA CLASS:
  // h'mta fr[gan med n'sta arrayid -> h'mta svaren (4st) med r[tt question id

  render() {
    return (
      <div className = "question">
        <h3>{this.state.questionTitle}</h3>
        {this.state.answers.forEach(answer => {
          <input type="radio" value={answer}/>
        })}
      </div>
    );
  }
}
