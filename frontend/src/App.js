import './App.css';
import React, { useState, useEffect } from 'react';
import axios from 'axios'

export default class App extends React.Component {
  render(){
    console.log('App.render()');
    // return (
    //   <ClassAPIService/>
    // );
    return (
      <FunctionAPIService/>
    );
  }
}

class ClassAPIService extends React.Component {
  constructor() {
    console.log('ClassAPIService.constructor()');
    super();

    this.state = {
      responseJSON:'not changed'
    }
  }

  render(){
    console.log('AxiosService.render()');

    axios.get('http://localhost:8080/devices/statuses')
      .then((response) => {
        console.log('ClassAPIService.getDerivedStateFromProps() response : ' + response.data[0]);
        console.log('ClassAPIService.getDerivedStateFromProps() response : ' + JSON.stringify(response.data[0]));
      
        this.setState({ responseJSON: JSON.stringify(response.data[0]) });
      }
    );

    return (
      <div>
        <p>
          class test<br/>
          {this.state.responseJSON}
        </p>
      </div>
    );
  }
}

function FunctionAPIService() {
  const [responseJSON, setResponseJSON] = useState('default value');
  
  function getRequest() {
    axios.get('http://localhost:8080/devices/statuses')
      .then((response) => {
        console.log('FunctionAPIService.responseJSONCallback() response : ' + JSON.stringify(response.data[0]));
        setResponseJSON(JSON.stringify(response.data[0]));
      }
    );
  }

  useEffect(getRequest, []);

  return (
    <div className='FunctionAPIService'>
      <p>
        function test<br/>
        {responseJSON}
      </p>
    </div>
  );
}