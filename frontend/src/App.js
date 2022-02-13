import './App.css';
import React, { useEffect, useState } from 'react';
import axios from 'axios'
import Chart from 'chart.js/auto'; 
import {Line, Bar, Radar, Doughnut, PolarArea, Bubble, Pie, Scatter} from 'react-chartjs-2';

const PERCENT = 100;

export default function App() {
  const [chartData, setChartData] = useState({
    data : {
      labels: [5],
      datasets: [
        {
          label: 'CPU 사용량(라인명)',
          fill: false,
          lineTension: 0.3,
          backgroundColor: 'rgba(75,192,192,1)',
          borderColor: 'rgba(0,0,0,1)',
          borderWidth: 2,
          data: [50.0]
        }
      ]
    },
    options : {
      responsive: true,
      plugins: {
        legend: {
          position: 'top',
        },
        title: {
          display: true,
          text: 'CPU 사용량(차트명)'
        }
      },
      scales: {
        y: {
          min: 0,
          max: 100,
        }
      }
    }	
  });
  const [cpuUsageArray, setCpuUsageArray] = useState([77.7]);
  const timeArray = Array.from({length: 60}, (_, i) => 60 - i);

  //일정 간격으로 request하여
  //cpu 사용량 배열을 최신화
  useEffect(() => {
    const interval = setInterval(() => {
      getRequest();
    }, 3000);
  
    return () => {
      clearInterval(interval);
    };
  }, []);

  //cpu 사용량 배열을 감시하고 변화가 있다면
  //차트 데이터 전체를 최신화
  useEffect(() => {
    console.log('setCpuUsageArray last element : ' + cpuUsageArray[59]);
    const newData = {
      data : {
        labels: [5],
        datasets: [
          {
            label: 'CPU 사용량(라인명)',
            fill: false,
            lineTension: 0.3,
            backgroundColor: 'rgba(75,192,192,1)',
            borderColor: 'rgba(0,0,0,1)',
            borderWidth: 2,
            data: [50.0]
          }
        ]
      },
      options : {
        responsive: true,
        plugins: {
          legend: {
            position: 'top',
          },
          title: {
            display: true,
            text: 'CPU 사용량(차트명)'
          }
        },
        scales: {
          y: {
            min: 0,
            max: 100,
          }
        }
      }	
    };
    newData.data.labels = timeArray;
    newData.data.datasets[0].data = cpuUsageArray;
    setChartData(newData);
  }, [cpuUsageArray]);

  //초기값은 타임스탬프 기준 (현재 시각 - 확인하고 싶은 시각) 전부 긁어와야 함
  function getRequest(minuteAgo=60) {
    const url = 'http://localhost:8080/devices/statuses';
    axios.get(url, {
      params: {
        minute_ago: minuteAgo
      }
    })
      .then((response) => {
        //JSON 가공
        //여러 개 받아온 데이터 길이 구해서 끝에서부터 일정량 잘라 사용할것임
        const length = Object.keys(response.data).length;
        
        for(let i=length-60; i<length; i++){
          cpuUsageArray.push(PERCENT-JSON.stringify(response.data[i]['cpu']['timePercentIdle']))
        }

        setCpuUsageArray([...cpuUsageArray]);
      }
    );
  }

  return (
    <div>
    <Bar 
      data={chartData.data}
      options={chartData.options}
    />
    <Line 
      data={chartData.data}
      options={chartData.options}
    />
    </div>
  );
}
