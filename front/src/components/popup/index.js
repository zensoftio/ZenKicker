import React, {Component} from 'react';
import styled from 'styled-components';
import {Button} from '../../components-ui/buttons/button';

const Content = styled.div`
  display: flex;
`;

const PopupContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 10;
`;

class Popup extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isPopupOpen: false
    }
  }

  onPopupOpen = () => this.setState({isPopupOpen: true})
  onPopupClose = () => this.setState({isPopupOpen: false})

  handleOnBackgroundClick = (e) => {
    if (e.target.classList.contains(PopupContainer.styledComponentId)) {
      this.onPopupClose();
      this.props.clearValues();
    }
  }

  render() {
    const {isPopupOpen} = this.state;
    const {children, buttonTitle} = this.props;

    return (
      <Content>
        <Button onClick={this.onPopupOpen}>{buttonTitle}</Button>
        {
          isPopupOpen &&
          <PopupContainer onClick={this.handleOnBackgroundClick}>
            {children}
          </PopupContainer>
        }
      </Content>
    )
  }
}

export default Popup