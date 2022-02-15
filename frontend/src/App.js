import './App.css';
import React, { useEffect, useState } from 'react';
import axios from 'axios'
import Chart from 'chart.js/auto'; 
import {Line, Bar, Radar, Doughnut, PolarArea, Bubble, Pie, Scatter} from 'react-chartjs-2';
import moment from 'moment';

const PERCENT_MIN = 0;
const PERCENT_MAX = 100;
const DATE_FORMAT = 'YYYY-MM-DD HH:mm:ss';

const refreshDelay = 5 * 1000; //밀리초

//시간 배열 길이 : 1분 ~ 1일
//기본값 : 5분
const timeMin = 60;
// const timeMax = 24 * 60 * 60;
const timeMax = 2000;
const timeDefault = 10 * 60;

let lastUpdated = moment(0);

export default function App() {
  const [chartData, setChartData] = useState(initialChartData);
  const [cpuUsageArray, setCpuUsageArray] = useState([]);
  const [timeArray, setTimeArray] = useState(Array.from({length: timeDefault}, (unUsedValueForIndex, index) => index));

  //interval 등록과 삭제 반복으로 일정 시간 간격마다 동작
  useEffect(() => {
    const interval = setInterval(() => {
      //데이터를 읽어올 시간 간격 정의
      //최근 업데이트가 지정한 시간 이내에 발생했다면
      //최근 업데이트 시간을 기준으로 데이터를 가져온다.
      const endTime = moment().local();
      const startTime = (lastUpdated > endTime.clone().subtract(timeDefault, 'seconds'))
       ? lastUpdated 
       : endTime.clone().subtract(timeDefault, 'seconds');
      lastUpdated = endTime;

      console.log('interval' + '\n'
      + ' start : ' + startTime.format(DATE_FORMAT) + '\n'
      + ' end   : ' + endTime.format(DATE_FORMAT));
      
      const timeLength = Math.round(moment.duration(endTime.diff(startTime)).asSeconds());
      console.log(timeLength);
      let newArray = Array.from({length: timeLength}, () => null);

      //API 요청 준비
      const url = 'http://localhost:8080';
      const path = '/devices/statuses';
      const axiosConfig = {
        params: {
          start: startTime.unix(),
          end : endTime.unix()
        }
      };

      //API 요청 및 콜백 등록
      axios.get(url + path, axiosConfig)
      .then((response) => {
        //결과가 없으면 
        if(Object.keys(response.data).length == 0){
          //시간 차이만큼 빈 배열 붙이고 리턴
          console.log("response 결과 없음");
          setCpuUsageArray(cpuUsageArray => [...newArray, ...cpuUsageArray]);
          return;
        }

        //결과값 JSON 처리
        for(let i=0; i<Object.keys(response.data).length; i++){
          const time = moment(response.data[i]['loggedTime'], DATE_FORMAT);
          const gapSecond = moment.duration(endTime.diff(time)).asSeconds();
          const insertIndex = Math.floor(gapSecond);

          newArray[insertIndex] =
            // PERCENT_MAX-
            JSON.stringify(response.data[i]['cpu']['timePercentIdle']);
          // responseTimes[insertIndex] =
          //   JSON.stringify(response.data[i]['loggedTime']);
          
          // console.log('기록된 시간   : ' + time.format(DATE_FORMAT) + '\n'
          //           + 'API 호출 시간 : ' + endTime.format(DATE_FORMAT));
          // console.log('시간차 : ' + gapSecond + '\n' 
          //           + '인덱스 : ' + insertIndex);
        }
        console.log(newArray);
  
        //최대 시간을 넘어간 데이터는 메모리에 저장하지 않는다.
        setCpuUsageArray(cpuUsageArray => [...newArray, ...cpuUsageArray]);
      });
    }, refreshDelay);
    return () => clearInterval(interval);
  }, []);
  
  //cpu 사용량 배열을 감시하고 변화가 있다면
  //차트 데이터 전체를 최신화
  useEffect(() => {
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
          x:{
            reverse: true,
          },
          y: {
            min: 0,
            max: 100,
          }
        }
      }	
    };
    newData.data.labels = timeArray;
    newData.data.datasets[0].data = cpuUsageArray;
    
    // console.log('cpuUsageArray : ' + cpuUsageArray.length);
    // console.log('cpuUsageArray : ' + newData.data.datasets[0].data);
    setChartData(newData);
  }, [cpuUsageArray]);

  return (
    <div>
      <p>lastUpdated : </p>
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

const initialChartData = {
  data : {
    labels: [],
    datasets: [
      {
        label: '',
        fill: false,
        lineTension: 0.3,
        backgroundColor: 'rgba(75,192,192,1)',
        borderColor: 'rgba(0,0,0,1)',
        borderWidth: 2,
        data: []
      }
    ]
  },
  options : {
    responsive: true,
    spanGaps: true,
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: true,
        text: ''
      }
    },
    scales: {
      y: {
        min: PERCENT_MIN,
        max: PERCENT_MAX,
      }
    }
  }	
};
