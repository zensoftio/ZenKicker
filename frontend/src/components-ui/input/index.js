import React from 'react';
import styled from 'styled-components';
import PropTypes from "prop-types";

export const Input = ({value, onChange, placeholder, type = 'text'}) => (
  <Content>
    <InputElement value={value} onChange={onChange} placeholder={placeholder} type={type}/>
  </Content>
)

Input.propTypes = {
  value: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  placeholder: PropTypes.string,
  type: PropTypes.oneOf(['text', 'password']),
}

const Content = styled.div`
  position: relative;
  width: 100%;
  padding: 10px;
  box-sizing: border-box;
`;

const InputElement = styled.input`
  font-size: 1em;
  padding: 10px;
  width: 100%;
  box-sizing: border-box;
  outline: none;
  text-align: left;
  background-color: hsl(0,0%,100%);
  border: solid 1px hsl(0,0%,80%);
  border-radius: 4px;
 
  &:focus {
    border-color: #2684FF;
  }
`;
