import React from 'react';
import styled from 'styled-components';
import {Link} from 'react-router-dom';

const Content = styled(Link)`
  display: flex;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	color: black;
	text-decoration: none;
	align-items: center;
	padding: 15px 20px;
	width: max-content;
	min-height: 80px;
	box-sizing: border-box;
	transition: margin .3s ease-in-out;
	span {
	  margin-right: 30px;
	}
  img {
    max-width: 50px;
    min-width: 50px;
    max-height: 50px;
    min-height: 50px;
    margin-left: 10px;
    border-radius: 100%;
    &:first-child {
      padding-left: 0;
    }
  }
  &:hover {
    margin: 5px 0;
    box-shadow: 0 6px 10px rgba(0,0,0,0.1);
    background-color: #fafafa
  }
`;

const Team = styled.div`
  display: flex;
`;

const Score = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0 40px;
  font-size: 1.3em;
`;

export const GameBlock = ({winner1Icon, winner2Icon, loser1Icon, loser2Icon, losersGoals}) => (
  <Content to={'/'}>
    <Team>
      {
        winner1Icon ?
          <img alt="avatar" src={`http://localhost/images/icons/${winner1Icon}`}/> :
          <img alt="avatar" src={'https://www.shareicon.net/data/2016/08/05/806962_user_512x512.png'} />
      }
      {
        winner2Icon ?
          <img alt="avatar" src={`http://localhost/images/icons/${winner2Icon}`}/> :
          <img alt="avatar" src={'https://www.shareicon.net/data/2016/08/05/806962_user_512x512.png'} />
      }
    </Team>
    <Score>10 : {losersGoals}</Score>
    <Team>
      {
        loser1Icon ?
          <img alt="avatar" src={`http://localhost/images/icons/${loser1Icon}`}/> :
          <img alt="avatar" src={'https://www.shareicon.net/data/2016/08/05/806962_user_512x512.png'} />
      }
      {
        loser2Icon ?
          <img alt="avatar" src={`http://localhost/images/icons/${loser2Icon}`}/> :
          <img alt="avatar" src={'https://www.shareicon.net/data/2016/08/05/806962_user_512x512.png'} />
      }
    </Team>
  </Content>
)
