import React from 'react';
import {Pie} from 'react-chartjs';

const PieChart = ({data, options}) => (
  <Pie data={data} options={options} width={500} height={250}/>
)

export default PieChart;

