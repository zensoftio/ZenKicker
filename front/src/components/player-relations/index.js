import React from 'react';
import styled from "styled-components";
import DoughnutChart from "../doughnut-chart";
import DropdownInput from "../../components-ui/dropdown-input";
import PieChart from "../pie-chart";
import {ChartColors} from "../../helpers/color-lib";

class PlayerRelations extends React.Component {
  constructor(props) {
    super(props);
    this.getOptions = this.getOptions.bind(this);
    this.state = {
      doughnutChartData: [],
      countGames: null,
      isDoughnutChartVisible: false,
      isPieChartVisible: true
    }
  }

  onPlayerChange = (option) => {
    if (!option) return this.setState({isPieChartVisible: true, isDoughnutChartVisible: false})

    const {winningPercentage, countGames} = this.getRelation(option.value);

    this.setState({
      doughnutChartData: this.setDoughnutChartData(winningPercentage),
      countGames: countGames,
      isPieChartVisible: false,
      isDoughnutChartVisible: true
    })
  }

  getRelation = (playerId) => this.props.relations.find(i => i.partnerId === playerId)

  setDoughnutChartData = (percent) => ([
    {
      value: percent,
      label: "Wins (%)",
      ...ChartColors[0]
    },
    {
      value: 100 - percent,
      label: "Loses (%)",
      ...ChartColors[1]
    }
  ])

  setPieChartData = (relations) =>
    relations.map((relation, index) => ({value: relation.countGames, label: relation.partnerName, ...ChartColors[index]}))

  getOptions = () => this.props.relations.map(i => ({value: i.partnerId, label: i.partnerName}))

  render() {
    const {relations} = this.props;
    if (!relations.length) return null;

    const {doughnutChartData, isPieChartVisible, isDoughnutChartVisible} = this.state;

    return (
      <Content>
        <Title>
          {
            isPieChartVisible ? 'Count games with each player' : 'Wins/loses with player'
          }
        </Title>
        {
          isPieChartVisible && <PieChart data={this.setPieChartData(relations)}/>
        }
        {
          isDoughnutChartVisible && <DoughnutChart data={doughnutChartData} gamesCount={this.state.countGames}/>
        }
        <DropdownInput isClearable options={this.getOptions()} onChange={this.onPlayerChange} placeholder='Select player'/>
      </Content>
    )
  }

}

export default PlayerRelations;

const Content = styled.div`
  display: flex;
  flex-direction: column;
`;

const Title = styled.div`
  text-align: center;
  font-size: 1.3em;
  margin: 50px 0 30px 0;
`;