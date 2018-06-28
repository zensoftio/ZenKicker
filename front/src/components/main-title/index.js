import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
	padding: 20px 20px 20px 100px;
	font-size: 22px;
`;

export const MainTitle = ({children}) => (
	<Container>
		{children}
	</Container>
)