import React from 'react';
import PropTypes from "prop-types";
import styled from "styled-components";
import DoughnutChart from "../doughnut-chart";
import DropdownInput from "../../components-ui/dropdown-input";
import PieChart from "../pie-chart";
import {ChartColors, OtherSectorColor} from "../../helpers/color-lib";
import {Title} from "../../components-ui/title";
import {RelationModel} from "../../common/global-prop-types";

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

  getRelation = (playerId) => this.props.relations.find(i => i.partner.id === playerId)

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

  buildOtherRelation = (playerRelations) => {
    const otherGames = this.props.gamesPlayed - playerRelations.map(rel => rel.value).reduce((acc, val) => acc + val);

    return {
      value: otherGames,
      label: `Other (${this.getPercentage(otherGames, this.props.gamesPlayed)}%)`,
      ...OtherSectorColor
    };
  };

  setPieChartData = (relations) => {
    const playerRelations = relations.map((relation, index) =>
      ({
        value: relation.countGames,
        label: `${relation.partner.fullName} (${this.getPercentage(relation.countGames, this.props.gamesPlayed)}%)`,
        ...ChartColors[index]
      }));

    return [...playerRelations, this.buildOtherRelation(playerRelations)];
  };

  getPercentage = (games, total) => (100 * games / total).toFixed(2);

  getOptions = () => this.props.relations.map(i => ({value: i.partner.id, label: i.partner.fullName}))

  render() {
    const {relations, isMobile} = this.props;

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
          isPieChartVisible && <PieChart data={this.setPieChartData(relations)} isMobile={isMobile}/>
        }
        {
          isDoughnutChartVisible && <DoughnutChart data={doughnutChartData} gamesCount={this.state.countGames} isMobile={isMobile}/>
        }
        <DropdownInput isClearable options={this.getOptions()} onChange={this.onPlayerChange} placeholder='Select player'/>
      </Content>
    )
  }

}

export default PlayerRelations;

PlayerRelations.propTypes = {
  relations: PropTypes.arrayOf(RelationModel),
  isMobile: PropTypes.bool.isRequired,
  gamesPlayed: PropTypes.number.isRequired,
}

const Content = styled.div`
  display: flex;
  flex-direction: column;
`;