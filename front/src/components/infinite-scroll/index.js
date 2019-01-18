import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';

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

export default InfiniteScrollComponent;