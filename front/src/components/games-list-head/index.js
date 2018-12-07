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
	align-items: center;
	padding: 15px 20px;
	width: 100%;
	min-width: 300px;
	box-sizing: border-box;
	font-size: 1.2em;
	margin-bottom: 10px;
	@media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 1em;
    padding: 10px;
  }
`;

const Template = styled.div`
	max-width: 50%;
  min-width: 50%;
  text-align: center;
	box-sizing: border-box;
`;

const Winners = styled(Template)`
  padding-right: 40px;
`;

const Losers = styled(Template)`
  padding-left: 40px;
`;
