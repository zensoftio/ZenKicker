import React, {Component} from 'react';
import PropTypes from "prop-types";
import styled from 'styled-components';
import {Button} from '../../components-ui/buttons/button';
import {Colors, MediaViews} from "../../helpers/style-variables";

class Popup extends Component {
  state = {
    isPopupOpen: false
  };

  onPopupOpen = () => {
    this.setState({isPopupOpen: true});
    document.getElementsByTagName('BODY')[0].style.overflow = 'hidden';
    if (this.props.loadData) {
      this.props.loadData();
    }
  };
  onPopupClose = () => {
    this.setState({isPopupOpen: false});
    document.getElementsByTagName('BODY')[0].style.overflow = 'auto';
  };

  handleOnBackgroundClick = (e) => {
    if (e.target.classList.contains(PopupWrapper.styledComponentId)) {
      this.onPopupClose();
      this.props.clearValues();
    }
  };

  render() {
    const {isPopupOpen} = this.state;
    const {children, buttonTitle, buttonIcon} = this.props;

    return (

      <Content>
        <Button buttonIcon={buttonIcon} onClick={this.onPopupOpen}>{buttonTitle}</Button>
        {isPopupOpen && <PopupWrapper onClick={this.handleOnBackgroundClick}>
          <PopupContainer>
            {children}
          </PopupContainer>
        </PopupWrapper>}
      </Content>

    );
  }
}

export default Popup;

Popup.propTypes = {
  buttonTitle: PropTypes.string,
  clearValues: PropTypes.func.isRequired
};

const Content = styled.div`
  display: flex;
`;

const PopupWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  height: 100vh;
  top: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 10;
  @media (max-width: ${MediaViews.MOBILE}px) {
    align-items: flex-start;
  }
`;

const PopupContainer = styled.div`
  background-color: #fff;
  padding: 40px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  border-radius: 2px;
  background-color: ${Colors.THEME_COLOR};
  & > div {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    height: max-content;
  }
  @media (max-width: ${MediaViews.MOBILE}px) {
    width: 100%;
    max-height: 100vh;
    border-radius: 0;
    padding: 20px;
    box-sizing: border-box;
    overflow-y: auto;
    & > div {
      height: 100%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
    }
  }
`;
