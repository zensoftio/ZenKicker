import React from 'react';
import styled from 'styled-components';
import {LineChart, Line, XAxis, YAxis, Tooltip, CartesianGrid} from 'recharts';
import {Colors} from '../../helpers/style-variables';

const PlayerStatistic = ({data, lineDataKey, title}) => {

  if(!data) return null;

  return (
    <div>
      <Title>{title}</Title>
      <LineChart width={900} height={400} data={data} margin={{top: 5, right: 30, left: 20, bottom: 5}}>
        <XAxis dataKey='week' stroke='#a5a5a5' reversed={true} tickMargin={15} padding={{left: 20}} axisLine={false}/>
        <YAxis tickLine={false} tickFormatter={(value) => (value < 1000 ? value : value / 1000 + 'k')} stroke='#a5a5a5'/>
        <Tooltip/>
        <CartesianGrid stroke="#f2f2f2" vertical={false}/>
        <Line type="monotone" dataKey={lineDataKey} stroke={Colors.MAIN_COLOR} />
      </LineChart>
    </div>
  )
}

export default PlayerStatistic;

const Title = styled.div`
  text-align: center;
  font-size: 1.3em;
  margin: 30px 0;
`;