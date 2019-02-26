import React from 'react';
import styled from 'styled-components';
import PropTypes from "prop-types";

import defaultPhoto from '../../shared/images/default-photo.png';

const Img = styled.img`
  max-width: 100%;  
`;

export const UserPhoto = ({photo}) => (
  photo ?
    <Img alt="avatar" src={`/images/icons/${photo}`}/> :
    <Img alt="avatar" src={defaultPhoto}/>
)

UserPhoto.propTypes = {
  photo: PropTypes.string
}

export default UserPhoto