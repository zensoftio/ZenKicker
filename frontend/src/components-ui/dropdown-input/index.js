import React from 'react';
import PropTypes from 'prop-types';
import styled from 'styled-components';
import Select from 'react-select'

const DropdownInput = ({options, onChange, placeholder, value, isClearable, defaultValue, onInputChange}) => (
  <Content>
    <CustomSelect value={value} options={options} onChange={onChange} placeholder={placeholder} isClearable={isClearable}
                  defaultValue={defaultValue} onInputChange={onInputChange}/>
  </Content>
)

export default DropdownInput;

DropdownInput.propTypes = {
  options: PropTypes.arrayOf(PropTypes.shape({
    value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
    label: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired
  })),
  onChange: PropTypes.func.isRequired,
  placeholder: PropTypes.string,
  value: PropTypes.shape({
    value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
    label: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired
  }),
  isClearable: PropTypes.bool,
  defaultValue: PropTypes.shape({
    value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
    label: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired
  }),
  onInputChange: PropTypes.func
}

const Content = styled.div`
  padding: 10px;
  
`;

const CustomSelect = styled(Select)`
  
`;