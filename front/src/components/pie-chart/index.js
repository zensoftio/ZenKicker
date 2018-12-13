import React from 'react';
import {Pie} from 'react-chartjs';

const PieChart = ({data, options, isMobile}) => {
  const width = isMobile ? window.outerWidth - 3 : 500;
  return <Pie data={data} options={options} width={width} height={250}/>
}

export default PieChart;

