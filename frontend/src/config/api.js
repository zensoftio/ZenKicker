import axios from 'axios';

export const configuration = Object.freeze (
	{
		apiPrefix: '/api',
		remoteApi: ''
	}
)

export const api = axios.create({
	// baseURL: 'http://localhost:8080'
});
