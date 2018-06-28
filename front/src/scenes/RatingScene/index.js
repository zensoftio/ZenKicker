import React from 'react';
import styled from 'styled-components';
import {MainTitle} from "../../components/main-title";
import {getTitleFromUrl} from "../../helpers/string-helpers";
import {Table} from '../../components/table';

const Content = styled.div`
	padding: 40px 20px 20px 20px;
	display: flex;
`;

const data = [
	{
		fullName: 'Test test 1',
		games: 0,
		rated: 0,
		rating: 0
	},
	{
		fullName: 'Test test 2',
		games: 0,
		rated: 0,
		rating: 0
	},
	{
		fullName: 'Test test 3',
		games: 0,
		rated: 0,
		rating: 0
	},
	{
		fullName: 'Test test 3',
		games: 0,
		rated: 0,
		rating: 0
	},
	{
		fullName: 'Test test 3',
		games: 0,
		rated: 0,
		rating: 0
	},
	{
		fullName: 'Test test 3',
		games: 0,
		rated: 0,
		rating: 0
	},
	{
		fullName: 'Test test 3',
		games: 0,
		rated: 0,
		rating: 0
	},
	{
		fullName: 'Test test 3',
		games: 0,
		rated: 0,
		rating: 0
	}
];

const RatingScene = ({match}) => (
	<div>
		<MainTitle>{getTitleFromUrl(match.url)}</MainTitle>
		<Content>
			<Table columns={getColumns()} data={data}/>
		</Content>
	</div>
)

const getColumns = () => [
	{
		Header: '',
		Cell: ({index}) => <span>{index + 1}</span>,
		width: 50,
		style: {
			textAlign: 'center'
		}
	},
	{
		Header: "Player",
		accessor: 'fullName',
		width: 250,
		style: {
			textAlign: 'center'
		}
	},
	{
		Header: "Games",
		accessor: 'games',
		width: 100,
		style: {
			textAlign: 'center'
		}
	},
	{
		Header: "Rated",
		accessor: 'rated',
		width: 150,
		style: {
			textAlign: 'center'
		}
	},
	{
		Header: "Rating",
		accessor: 'rating',
		width: 150,
		style: {
			textAlign: 'center'
		}
	}
];

export default RatingScene;