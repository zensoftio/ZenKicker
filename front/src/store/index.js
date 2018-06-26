import { createStore, applyMiddleware, compose, combineReducers } from 'redux';
import thunk from 'redux-thunk';
import reduxLogger from 'redux-logger';
import reducers from '../reducers';

const reduxStore = (initialState) => (
    createStore(combineReducers(reducers), initialState, compose(
        applyMiddleware(thunk),
        applyMiddleware(reduxLogger)
    ))
)

export default reduxStore;