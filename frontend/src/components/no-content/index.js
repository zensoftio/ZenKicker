import React from 'react';
import styled from 'styled-components';

export const NoContent = () => (
  <Content>No content</Content>
)

const Content = styled.div`
  width: 100%;
  text-align: center;
  color: black;
  opacity: 0.5;
  padding: 10px 0;
`;