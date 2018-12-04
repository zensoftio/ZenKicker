import React from 'react';
import styled from 'styled-components';
import {Doughnut} from 'react-chartjs';

const DoughnutChart = ({data, options, gamesCount, isMobile}) => {

  const width = isMobile ? window.outerWidth : 500;
  return (
    <Content>
      <Doughnut data={data} options={options} width={width} height={250}/>
      <GamesCount>{gamesCount}<span>games</span></GamesCount>
    </Content>
  )
}

export default DoughnutChart;

const Content = styled.div`
  position: relative;
`;

const GamesCount = styled.div`
  position: absolute;
  width: max-content;
  display: flex;
  flex-direction: column;
  align-items: center;
  top: 40%;
  left: 0;
  right: 0;
  margin: auto;
  font-size: 1.2em;
`;
