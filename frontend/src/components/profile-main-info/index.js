import React, {Component} from 'react';
import PropTypes from "prop-types";
import {Link} from 'react-router-dom';
import styled from 'styled-components';
import {Colors, MediaViews} from '../../helpers/style-variables';
import {RelationDashboardModel} from "../../common/global-prop-types";
import EditProfileInfo from "../edit-profile-info";
import UserPhoto from "../../components-ui/user-photo";
import AchievementsBlock from "../achievements-block";

class ProfileMainInfo extends Component {

  render() {
    const {
      rating, countGames, rated, iconPath, countLosses, countWins, goalsAgainst, goalsFor, currentLossStreak, achievements,
      currentWinStreak, longestLossStreak, longestWinStreak, winningPercentage, bestPartner, favoritePartner, worstPartner,
      fullName, isCurrent, email
    } = this.props;

    const bestPartnerObj = bestPartner && {
      id: bestPartner.partner.id,
      fullName: bestPartner.partner.fullName,
      winningPercentage: bestPartner.winningPercentage,
      countGames: bestPartner.countGames,
      countWins: bestPartner.countWins,
    };

    const worstPartnerObj = worstPartner && {
      id: worstPartner.partner.id,
      fullName: worstPartner.partner.fullName,
      winningPercentage: worstPartner.winningPercentage,
      countGames: worstPartner.countGames,
      countWins: worstPartner.countWins,
    };

    const favoritePartnerObj = favoritePartner && {
      id: favoritePartner.partner.id,
      fullName: favoritePartner.partner.fullName,
      countGames: favoritePartner.countGames,
    };

    return (

      <Content>
        <div>
          <ProfilePhoto>
            <UserPhoto photo={iconPath}/>
          </ProfilePhoto>
          <AchievementsBlock achievements={achievements}/>
        </div>
        <Info>
          <UsernameSettings>
            <Username>{fullName}</Username>
            {isCurrent &&
            <EditProfileInfo playerId={this.props.id} iconPath={iconPath} getCurrent={this.props.getCurrent}
                             getPlayer={this.props.getPlayer} fullName={fullName} email={email}/>}
          </UsernameSettings>
          <Email>{email}</Email>
          <StatisticsContent>
            <Statistics>
              <Field>Rating: <span>{rating}</span></Field>
              <Field>Games played: <span>{countGames}</span></Field>
              <Field>Games rated: <span>{rated}</span></Field>
              <Field>Games won: <span>({winningPercentage}%) {countWins}</span></Field>
              <Field>Games lost: <span>({Math.round((100 - winningPercentage) *100) / 100}%) {countLosses}</span></Field>
              <Field>Goals for: <span>{goalsFor}</span></Field>
              <Field>Goals against: <span>{goalsAgainst}</span></Field>
              <Field>Current loss streak: <span>{currentLossStreak}</span></Field>
              <Field>Longest loss streak: <span>{longestLossStreak}</span></Field>
              <Field>Current win streak: <span>{currentWinStreak}</span></Field>
              <Field>Longest win streak: <span>{longestWinStreak}</span></Field>
              {bestPartnerObj && <Field>
                Best partner:
                <PartnerBlock>
                  <span><Link to={`/players/${bestPartnerObj.id}`}>{bestPartnerObj.fullName}</Link></span>
                  <span>{bestPartnerObj.countWins} wins in {bestPartnerObj.countGames} matches ({bestPartnerObj.winningPercentage}%)</span>
                </PartnerBlock>
              </Field>}
              {worstPartnerObj && <Field>
                Worst partner:
                <PartnerBlock>
                  <span><Link to={`/players/${worstPartnerObj.id}`}>{worstPartnerObj.fullName}</Link></span>
                  <span>{worstPartnerObj.countWins} wins in {worstPartnerObj.countGames} matches ({worstPartnerObj.winningPercentage}%)</span>
                </PartnerBlock>
              </Field>}
              {favoritePartnerObj && <Field>
                Favorite partner:
                <PartnerBlock>
                  <span><Link to={`/players/${favoritePartnerObj.id}`}>{favoritePartnerObj.fullName}</Link></span>
                  <span>{favoritePartnerObj.countGames} matches together</span>
                </PartnerBlock>
              </Field>}
            </Statistics>
          </StatisticsContent>
        </Info>
      </Content>

    );
  }
}

export default ProfileMainInfo;

ProfileMainInfo.propTypes = {
  countGames: PropTypes.number.isRequired,
  rated: PropTypes.number.isRequired,
  rating: PropTypes.number.isRequired,
  id: PropTypes.string.isRequired,
  goalsAgainst: PropTypes.number.isRequired,
  goalsFor: PropTypes.number.isRequired, ...RelationDashboardModel.isRequired,
  fullName: PropTypes.string.isRequired,
  email: PropTypes.string.isRequired,
  iconPath: PropTypes.string,
  countWins: PropTypes.number.isRequired,
  winningPercentage: PropTypes.number.isRequired,
  currentLossStreak: PropTypes.number.isRequired,
  currentWinStreak: PropTypes.number.isRequired,
  longestLossStreak: PropTypes.number.isRequired,
  longestWinStreak: PropTypes.number.isRequired,
  isCurrent: PropTypes.bool.isRequired,
  getCurrent: PropTypes.func.isRequired,
  getPlayer: PropTypes.func.isRequired,
};

const Content = styled.div`
  display: flex;
  width: 900px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    width: 100%;
    flex-direction: column;
    align-items: center;
  }
`;

const Info = styled.div`
  width: 100%;
`;

const UsernameSettings = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const StatisticsContent = styled.div`
  padding: 20px 20px 0 20px;
  display: flex;
  justify-content: space-between;
  @media (max-width: ${MediaViews.MOBILE}px) {
    padding: 10px;
  }
`;
const Statistics = styled.div`
  width: 72%;
  @media (max-width: ${MediaViews.MOBILE}px) {
    width: 100%;
  }
`;

const Field = styled.div`
  font-size: 1.1em;
  padding-bottom: 10px;
  display: flex;
  justify-content: space-between;
  span {
    color: ${Colors.MAIN_COLOR};
  }
  &:last-child {
    padding-bottom: 0;
  }
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 1em;
  }
`;

const PartnerBlock = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  a {
    text-decoration: none;
    color: ${Colors.MAIN_COLOR};
    &:hover {
      text-decoration: underline;
    }
  }
  span {
    padding-left: 10px;
    color: ${Colors.MAIN_COLOR};
    &:last-child {
      font-size: 0.7em;
    }
  }
`;

const Username = styled.div`
  font-size: 1.7em;
  padding: 5px 10px;
  width: 400px;
  box-sizing: border-box;
`;

const Email = styled.div`
  font-size: 1em;
  padding: 5px 10px;
  font-style: italic;
`;

const ProfilePhoto = styled.div`
  min-width: 150px;
  max-width: 150px;
  min-height: 150px;
  max-height: 150px;
  margin: 0 20px;
  position: relative;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 100%;
`;
