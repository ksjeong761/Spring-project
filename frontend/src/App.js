import './App.css';
import React, { useRef, useEffect, useState } from 'react';
import ReactDOM, { render } from 'react-dom';

import axios from 'axios'
import Chart from 'chart.js/auto'; 
import {Line} from 'react-chartjs-2';

const initialChartData = {
  data: {
    labels: ['시간1', '시간2', '시간3', '시간4', '시간5'],
    datasets: [
      {
        label: 'CPU 사용량(라인명)',
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
        text: 'CPU 사용량(차트명)'
      }
    }
  }
};

export default function App() {
  //state 변수 선언 : chartData
  //setter : setChartData
  //값 초기화 : initialChartData
  const [chartData, setChartData] = useState(initialChartData);

  function getRequest() {
    axios.get('http://localhost:8080/devices/statuses')
      .then((response) => {
        //JSON 가공
        //여러 개 받아온 데이터 길이 구해서 끝에서부터 일정량 잘라 사용할것임
        const responseArrayLength = Object.keys(response.data).length;
        const actuallyShowingLength = 60;
        const cpuUsageArray = [];
        const timeArray = [];

        for(let i=responseArrayLength-actuallyShowingLength; i<responseArrayLength; i++){
          //console.log('App.getRequest() : ' +  JSON.stringify(response.data[i]['cpu']['timePercentIdle']));
          //console.log('App.getRequest() : ' +  JSON.stringify(response.data[i]['timestamp']));
          cpuUsageArray.push(100-JSON.stringify(response.data[i]['cpu']['timePercentIdle']))
          timeArray.push(responseArrayLength-i);
        }

        const newData = initialChartData;
        newData.data.labels = timeArray;
        newData.data.datasets[0].data = cpuUsageArray;

        //chartData에 데이터 넣는게 목표
        setChartData(newData)
      }
    );
  }

  useEffect(getRequest, []);
  
  return (
    <Line 
      data={chartData.data}
      options={chartData.options}
    />
  );
}