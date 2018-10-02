import React from 'react';
import styled from 'styled-components/typings/styled-components';

const Content = styled.div`
  display: flex;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	align-items: center;
	padding: 15px;
	width: 50%;
	span {
	  margin-right: 30px;
	}
  img {
    width: 50px;
    height: 50px;
  }
`;

export const ProfileBlock = ({username, gamesCount, rated, rating}) => (
  <Content>
    <span>1</span>
    <img src={'https://www.shareicon.net/data/2016/08/05/806962_user_512x512.png'} />
    <span>{username}</span>
    <span>{gamesCount}</span>
    <span>{rated}</span>
    <span>{rating}</span>
  </Content>
)
