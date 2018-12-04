import React from 'react';
import styled from 'styled-components';
import {MediaViews} from "../../helpers/style-variables";

const GamesListHead = () => (
  <Content>
    <Winners>Winners</Winners>
    <Losers>Losers</Losers>
  </Content>
)

export default GamesListHead;

const Content = styled.div`
  display: flex;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	align-items: center;
	padding: 15px 20px;
	width: 100%;
	min-width: 300px;
	box-sizing: border-box;
	font-size: 1.2em;
	@media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 1.1em;
  }
`;

const Winners = styled.div`
	max-width: 50%;
  min-width: 50%;
  text-align: center;
  padding-right: 40px;
	box-sizing: border-box;
	border-right: solid 1px #e0e0e0;
`;

const Losers = styled.div`
	max-width: 50%;
  min-width: 50%;
  padding-left: 40px;
  text-align: center;
	box-sizing: border-box;
`;
