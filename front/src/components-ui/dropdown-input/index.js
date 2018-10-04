import React, {Component} from 'react';
import styled from 'styled-components';
import Select from 'react-select'

const Content = styled.div`
  padding: 10px;
`;

const CustomSelect = styled(Select)`
  
`;

class DropdownInput extends Component {
  constructor(props) {
    super(props);
    this.state = {

    }
  }

  render() {
    const {data, onChange, placeholder} = this.props;

    return (
      <Content>
        <CustomSelect options={data} onChange={onChange} placeholder={placeholder}/>
      </Content>
    )
  }
}

export default DropdownInput