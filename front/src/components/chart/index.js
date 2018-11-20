import React from 'react';
import styled from 'styled-components';
import {LineChart, Line, XAxis, YAxis, Tooltip, CartesianGrid} from 'recharts';
import {Colors} from '../../helpers/style-variables';

const Chart = ({data, lineDataKey, title, width = 900, height = 200, xDataKey}) => {

  if(!data || !data.length) return null;

  return (
    <div>
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
    </div>
  )
}

export default Chart;

const Title = styled.div`
  text-align: center;
  font-size: 1.3em;
  margin: 30px 0;
`;