import React from 'react';
import PropTypes from "prop-types";
import styled from 'styled-components';

import uploadIco from '../../shared/images/icons/upload.png';
import UserPhoto from '../../components-ui/user-photo';
import {Colors, MediaViews} from "../../helpers/style-variables";

const ProfilePhotoBlock = ({onPhotoChange, iconPath, error}) => {
  const id = iconPath ? iconPath + Math.random() : Math.random();
  return (

    <>
      <ProfilePhoto>
        <UserPhoto photo={iconPath}/>
        <Button htmlFor={id}>
          <input id={id} type="file" onChange={onPhotoChange}/>
        </Button>
      </ProfilePhoto>

      <ErrorText>{error}</ErrorText>
    </>

  );
};

export default ProfilePhotoBlock;

ProfilePhotoBlock.propTypes = {
  iconPath: PropTypes.string,
  onPhotoChange: PropTypes.func.isRequired,
  error: PropTypes.string
};

const ProfilePhoto = styled.div`
  min-width: 150px;
  max-width: 150px;
  min-height: 150px;
  max-height: 150px;
  margin: 0 20px;
  position: relative;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 100%;
  @media (max-width: ${MediaViews.MOBILE}px) {
    min-width: 100px;
    max-width: 100px;
    min-height: 100px;
    max-height: 100px;
  }
`;

const Button = styled.label`
  cursor: pointer;
  border-radius: 100%;
  position: absolute;
  left: 0;
  top:0;
  bottom: 0;
  right: 0;
  
  input{
    display: none;
  }
  &:hover {
    background-color: rgba(0, 0, 0, 0.5);
    background-image: url(${uploadIco});
    background-repeat: no-repeat;
    background-position: center;
    background-size: 40px 40px;
  }
`;

const ErrorText = styled.div`
  color: ${Colors.ERROR_COLOR};
  padding: 0 10px;
`;
