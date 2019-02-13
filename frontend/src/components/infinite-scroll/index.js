import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import PropTypes from "prop-types";

const InfiniteScrollComponent = ({children, data, totalCount, onLoadMore}) => (
  <InfiniteScroll
    loadMore={onLoadMore}
    hasMore={totalCount > data.length}
    loader={<div key={0}>Loading...</div>}
    useWindow={false}
  >
    {children}
  </InfiniteScroll>
)

InfiniteScrollComponent.propTypes = {
  children: PropTypes.oneOfType([PropTypes.element, PropTypes.array]).isRequired,
  data: PropTypes.array.isRequired,
  totalCount: PropTypes.number.isRequired,
  onLoadMore: PropTypes.func.isRequired,
}

export default InfiniteScrollComponent;