import React, {Component} from 'react';
import styled from 'styled-components';
import {updatePhoto} from '../../actions/user';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getCurrent} from '../../actions';

const ProfilePhoto = styled.div`
  width: 150px;
  min-width: 150px;
  height: 150px;
  padding: 0 20px;
  position: relative;
  img {
    border-radius: 100%;
    width: 100%;
    height: 100%;
  }
`;

const Button = styled.label`
  cursor: pointer;
  
  position: absolute;
  left: 0;
  top:0;
  bottom: 0;
  right: 0;
  
  input{
    display: none;
  }
`;

class ProfilePhotoBlock extends Component {
  constructor(props) {
    super(props);
    this.state = {
      file: null,
    }
  }

  onChange = (e) => {
    this.setState({file: e.target.files[0]});
    const data = new FormData();
    data.append('file', e.target.files[0]);
    this.uploadPhoto(data);
  }

  uploadPhoto = async (data) => {
    try {
      await updatePhoto(data);
      this.props.actions.getCurrent();
    } catch (err) {
      console.log(err.response.data)
    }
  }

  render() {
    const {currentUser, isCurrent} = this.props;
    const id = currentUser.iconName + Math.random();

    return (
      <ProfilePhoto>
        {
          currentUser.iconName ?
            <img alt="avatar" src={`http://localhost/images/icons/${currentUser.iconName}`}/> :
            <img alt="avatar" src={'https://www.shareicon.net/data/2016/08/05/806962_user_512x512.png'} />
        }
        {
          isCurrent &&
          <Button htmlFor={id} >
            <input id={id} type="file" onChange={this.onChange}/>
          </Button>
        }
      </ProfilePhoto>
    )
  }
}


const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    currentUser: state.user.current
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getCurrent
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default connect(mapStateToProps, mapDispatchToProps)(ProfilePhotoBlock);