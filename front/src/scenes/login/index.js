import React from 'react';

export default class LoginScene extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			login: '',
			password: ''
		};
	}

	onSubmit = () => {
		console.log('login', this.state.login, 'pass', this.state.password);
	}

	onLoginChange = (value) => this.setState({login: value})
	onPasswordChange = (value) => this.setState({password: value})

	render() {
		return (
			<div>
				<input value={this.state.login} placeholder="Login" type="text" required
				       onChange={(e) => this.onLoginChange(e.target.value)}/>
				<input value={this.state.password} placeholder="Password" type="password" required
				       onChange={(e) => this.onPasswordChange(e.target.value)}/>
				<button onClick={this.onSubmit}>submit</button>
			</div>
		)
	}
}
