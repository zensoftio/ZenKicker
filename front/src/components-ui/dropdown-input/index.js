import React from 'react';
import styled from 'styled-components';
import Select from 'react-select'

const DropdownInput = ({options, onChange, placeholder, value, isClearable, defaultValue}) => (
  <Content>
    <CustomSelect value={value} options={options} onChange={onChange} placeholder={placeholder} isClearable={isClearable}
                  defaultValue={defaultValue}/>
  </Content>
)

export default DropdownInput;

const Content = styled.div`
  padding: 10px;
  
`;

const CustomSelect = styled(Select)`
  
`;