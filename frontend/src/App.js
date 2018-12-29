import React, { Component } from 'react';
import './App.css';
import Symptoms from './components/symptoms';
import RecordHistory from './components/recordhistory';
import FollowUp from './components/followup';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

const loginComponent = (props) => {    
    return (
        <div className="login-page">
            <div className="form">
                <form className="login-form">
                <input type="text" placeholder="username"/>
                <input type="password" placeholder="password"/>
                <button onClick={(e) => {
                    e.preventDefault();
                    window.location.href = "http://18.191.38.211:3000/diagnosis/";
                }}>login</button>
                </form>
            </div>
        </div>
    );
};

const Navigator = (props) => {
    return (
        <div>
        <nav id="navbar">
            <ul>
                <li>
                    <a className="active" href="http://18.191.38.211:3000/diagnosis/">Diagnosis</a>
                </li>
                <li>
                    <a href="http://18.191.38.211:3000/RecordHistory/">Record History</a>
                </li>
                <li>
                    <a href="http://18.191.38.211:3000/FollowUp/">Follow Up</a>
                </li>
            </ul>
        </nav>
        </div>
    );
}

const diagnosisComp = (props) => {
    return (
        <div>
        <Navigator />
        <Symptoms />
        </div>
    );
}
        
const recordComp = (props) => {
    return (
        <div>
        <Navigator />
        <RecordHistory />
        </div>
    );
}
        
const followComp = (props) => {
    return (
        <div>
        <Navigator />
        <FollowUp />
        </div>
    );
}

class App extends Component {

  render() {
    return (
    <Router>
    <div >
      <Link to="/"></Link>
      <Link to="/diagnosis"></Link>
      <Link to="/RecordHistory/"></Link>
      <Link to="/FollowUp/"></Link>
      <Route path="/" exact component={loginComponent} />
      <Route path="/diagnosis" exact component={diagnosisComp} />
      <Route path="/RecordHistory" exact component={recordComp} />
      <Route path="/FollowUp" exact component={followComp} />
    </div>
  </Router>
    );
  }
};

export default App;