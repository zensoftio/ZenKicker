import React from 'react';
import styled from 'styled-components';
import defaultPhoto from '../../shared/images/default-photo.png';

const Img = styled.img`
  max-width: 100%;  
`;

export const UserPhoto = ({photo}) => (
  photo ?
    <Img alt="avatar" src={`http://localhost/images/icons/${photo}`}/> :
    <Img alt="avatar" src={defaultPhoto} />
)

export default UserPhoto