import React, {Component} from 'react';
import styled from 'styled-components';
import UsernameBlock from '../username-block';

const Content = styled.div`
  display: flex;
  width: 100%;
`;

const ProfilePhoto = styled.div`
  width: 150px;
  min-width: 150px;
  height: 150px;
  padding: 0 20px;
  
  img {
    width: 100%;
    height: 100%;
  }
`;

const Info = styled.div`
  width: 100%;
`;

const StatisticsContent = styled.div`
  padding: 20px 0 0 20px;
`;

const Statistics = styled.div`
  font-size: 1.3em;
  padding-bottom: 10px;
  
  &:last-child {
    padding-bottom: 0;
  }

  span {
    font-weight: 100;
  }
`;

class ProfileMainInfo extends Component {

  render() {
    const {rating, countGames, rated, isCurrent} = this.props;

    return (
      <Content>
        <ProfilePhoto>
          <img alt="avatar" src={'https://www.shareicon.net/data/2016/08/05/806962_user_512x512.png'} />
        </ProfilePhoto>
        <Info>
          <UsernameBlock isCurrent={isCurrent}/>
          <StatisticsContent>
            <Statistics>Rating: <span>{rating}</span></Statistics>
            <Statistics>Games played: <span>{countGames}</span></Statistics>
            <Statistics>Games rated: <span>{rated}</span></Statistics>
          </StatisticsContent>
        </Info>
      </Content>
    )
  }
}

export default ProfileMainInfo