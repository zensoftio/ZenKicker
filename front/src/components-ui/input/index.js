import React from 'react';
import styled from 'styled-components';

const Content = styled.div`
  position: relative;
  margin: auto;
  width: 200px;
  margin-bottom: 20px;
  
  span {
    position: absolute;
    bottom: 0;
    left: 0;
    height: 2px;
    width: 100%;
    background red;
    transform: scaleX(0);
    transform-origin: 0 0;
    transition: all .15s ease;
  }
`;

const InputElement = styled.input`
  font-size: 1em;
  padding: 10px 0;
  width: 100%;
  box-sizing: border-box;
  border: none;
  border-left: solid 3px transparent;
  outline: none;
  text-align: center;
 
  &:focus {
    background: none;
    outline: none;
    + span {
      color red;
      transform: translateY(-26px) scale(.75);
      transform: scaleX(1);
    }
  }
`;

export const Input = ({value, onChange, placeholder}) => (
  <Content>
    <InputElement value={value} onChange={onChange}
                   placeholder={placeholder}/>
    <span/>
  </Content>
)
