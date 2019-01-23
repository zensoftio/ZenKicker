import React, {Component} from 'react';
import PropTypes from "prop-types";
import styled from 'styled-components';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getAllGames, appendToGames, getAllPlayers} from '../../actions';
import {withRouter} from 'react-router-dom';
import AllGames from '../../components/all-games';
import {Colors, MediaViews} from "../../helpers/style-variables";
import {GameModel} from "../../common/global-prop-types";

class GamesScene extends Component {

  componentDidMount() {
    const {getAllGames, getAllPlayers} = this.props.actions;
    getAllGames();
    getAllPlayers();
  }

  render() {
    const {games, actions} = this.props;

    return (
      <Content>
        <GamesCount>{games.totalCount} <span>games played</span></GamesCount>
        <AllGames games={games.list.length ? games.list : []} appendToGames={actions.appendToGames} totalCount={games.totalCount}/>
      </Content>

    );
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    games: state.game.games,
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getAllGames,
    appendToGames,
    getAllPlayers
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(GamesScene));

GamesScene.propTypes = {
  games: PropTypes.shape({
    list: PropTypes.arrayOf(GameModel),
    totalCount: PropTypes.number.isRequired
  }).isRequired,
}

const Content = styled.div`
  display: flex;
  justify-content: space-between;
	flex-direction: column;
	align-items: center;
`;

const GamesCount = styled.div`
  font-size: 3.7em;
  margin-bottom: 20px;
  color: ${Colors.MAIN_COLOR};
  padding-left: 125px;
  span {
    font-size: .3em;
  }
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 2.5em;
    padding-left: 0;
  }
`;
