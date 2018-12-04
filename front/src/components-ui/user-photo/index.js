import React from 'react';
import styled from 'styled-components';
import defaultPhoto from '../../shared/images/default-photo.png';

const Img = styled.img`
  max-width: 100%;  
`;

export const UserPhoto = ({iconPath}) => (
  iconPath ?
    <Img alt="avatar" src={iconPath}/> :
    <Img alt="avatar" src={defaultPhoto} />
)

export default UserPhoto