import React from 'react';
import styled from 'styled-components';
import Select from 'react-select'

const DropdownInput = ({options, onChange, placeholder, value, isClearable, defaultValue, onInputChange}) => (
  <Content>
    <CustomSelect value={value} options={options} onChange={onChange} placeholder={placeholder} isClearable={isClearable}
                  defaultValue={defaultValue} onInputChange={onInputChange}/>
  </Content>
)

export default DropdownInput;

const Content = styled.div`
  padding: 10px;
  
`;

const CustomSelect = styled(Select)`
  
`;