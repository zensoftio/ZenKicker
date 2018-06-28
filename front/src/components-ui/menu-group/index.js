import React from 'react';
import styled from 'styled-components';

const Content = styled.section`
	width: 100%;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  cursor: pointer;
  
`;

const List = styled.div`
	width: 100%;
	display: ${props => props.isOpen ? 'flex' : 'none'};
	flex-direction: column;
	align-items: start;
	
	&>a {
		padding-left: 35px;
	}
`;

const Title = styled.div`
  padding: 12px 25px;
  color: red;
`;

export class MenuGroup extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			toggle: true
		}
	}

	isOpen = () => this.setState({toggle: !this.state.toggle})

	render() {
		const {title, children} = this.props;

		return (
			<Content>
				<Title onClick={this.isOpen}>{title}</Title>
				<List isOpen={this.state.toggle}>
					{children}
				</List>
			</Content>
		)
	}
}