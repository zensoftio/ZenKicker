import React from 'react';
import PropTypes from "prop-types";
import styled from 'styled-components';
import {LineChart, Line, XAxis, YAxis, Tooltip, CartesianGrid} from 'recharts';
import {Colors, MediaViews} from '../../helpers/style-variables';
import {Title} from "../../components-ui/title";

const Chart = ({data, lineDataKey, title, isMobile, xDataKey}) => {

  if(!data || !data.length) return null;

  const width = isMobile ? window.outerWidth - 50 : 900;
  const height = 200;

  return (
    <Content>
      <Title>{title}</Title>
      <LineChart width={width} height={height} data={data} margin={{top: 5, bottom: 5, left: 30, right: 30}}>
        <XAxis dataKey={xDataKey} stroke='#a5a5a5' tickMargin={15} padding={{left: 30}} axisLine={false} tickLine={false}
               tickCount={7}/>
        <YAxis tickFormatter={(value) => (value < 1000 ? value : value / 1000 + 'k')} stroke='#a5a5a5' tickCount={4}
               mirror={true} domain={['dataMin', 'dataMax']}/>
        <Tooltip/>
        <CartesianGrid stroke="#f2f2f2" vertical={true} horizontal={false}/>
        <Line type="monotone" dataKey={lineDataKey} stroke={Colors.MAIN_COLOR} />
      </LineChart>
    </Content>
  )
}

export default Chart;

Chart.propTypes = {
  data: PropTypes.arrayOf(PropTypes.object).isRequired,
  lineDataKey: PropTypes.string.isRequired,
  title: PropTypes.string.isRequired,
  isMobile: PropTypes.bool.isRequired,
  xDataKey: PropTypes.string.isRequired,
}

const Content = styled.div`
  @media (max-width: ${MediaViews.MOBILE}px) {
    .recharts-cartesian-axis-tick {
      font-size: 0.8em;
    }
  }
`;
