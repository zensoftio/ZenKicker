import React from 'react';
import styled from 'styled-components';

import defaultPhoto from '../../shared/images/default-photo.png';

export const UserPhoto = ({photo}) => (
  photo ?
                  <img alt="avatar" src={`http://localhost/images/icons/${photo}`}/> :
                  <img alt="avatar" src={defaultPhoto} />
)

export default UserPhoto