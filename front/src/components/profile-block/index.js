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
	transition: margin .3s ease-in-out;
	span {
	  margin-right: 30px;
	}
  img {
    max-width: 50px;
    min-width: 50px;
    max-height: 50px;
    min-height: 50px;
    margin-left: 20px;
    border-radius: 100%;
  }
  &:hover {
    margin: 5px 0;
    box-shadow: 0 6px 10px rgba(0,0,0,0.1);
    background-color: #fafafa
  }
`;
const Template = styled.div`
  padding-left: 20px;
  overflow: hidden;
  text-overflow: ellipsis;
`;

const Index = styled.div`
  max-width: 40px;
  min-width: 40px;
  overflow: hidden;
  text-overflow: ellipsis;
`;

const Username = styled(Template)`
  max-width: 200px;
  min-width: 200px;
`;

const Statistics = styled(Template)`
  max-width: 100px;
  min-width: 100px;
`;

export const ProfileBlock = ({index, id, username, countGames, rated, rating, iconName}) => (
  <Content to={`/player/${id}`}>
    <Index>{index}</Index>
    {
      iconName ?
        <img alt="avatar" src={`http://localhost/images/icons/${iconName}`}/> :
        <img alt="avatar" src={'https://www.shareicon.net/data/2016/08/05/806962_user_512x512.png'} />
    }
    <Username>{username}</Username>
    <Statistics>{countGames}</Statistics>
    <Statistics>{rated}</Statistics>
    <Statistics>{rating}</Statistics>
  </Content>
)
