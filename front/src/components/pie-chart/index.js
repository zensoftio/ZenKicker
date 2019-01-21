import React from 'react';
import PropTypes from "prop-types";
import {Pie} from 'react-chartjs';

const PieChart = ({data, options, isMobile}) => {
  const width = isMobile ? window.outerWidth - 3 : 500;
  return <Pie data={data} options={options} width={width} height={250}/>
}


export default PieChart;

PieChart.propTypes = {
  data: PropTypes.arrayOf(PropTypes.shape({
    value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
    label: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
    color: PropTypes.string,
    highlight: PropTypes.string
  })).isRequired,
  options: PropTypes.arrayOf(PropTypes.shape({
    value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
    label: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired
  })),
  isMobile: PropTypes.bool.isRequired,
}