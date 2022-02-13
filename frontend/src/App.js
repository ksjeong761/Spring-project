import './App.css';
import React, { useRef, useEffect, useState } from 'react';
import ReactDOM, { render } from 'react-dom';

import axios from 'axios'
import Chart from 'chart.js/auto'; 
import {Line} from 'react-chartjs-2';

export default class App extends React.Component {
  constructor() {
    super();
    this.state = {
      data: {
        labels: ['시간1', '시간2', '시간3', '시간4', '시간5'],
        datasets: [
          {
            label: '데이터 이름',
            fill: false,
            lineTension: 0.3,
            backgroundColor: 'rgba(75,192,192,1)',
            borderColor: 'rgba(0,0,0,1)',
            borderWidth: 2,
            data: [0, 75, 50, 25, 100]
          }
        ]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'top',
          },
          title: {
            display: true,
            text: '차트 제목'
          }
        }
      }
    };
  }

  render() {
    return (
      <Line 
        data={this.state.data}
        options={this.state.options}
      />
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
        {this.state.responseJSON}
      </p>
    </div>
  );
}