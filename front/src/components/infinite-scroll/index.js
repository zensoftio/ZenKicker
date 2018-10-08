import React, {Component} from 'react';
import InfiniteScroll from 'react-infinite-scroller';

class InfiniteScrollComponent extends Component {

  onLoadMore = () => {
    this.props.onLoadMore()
  }
  render() {
    const {children, data, totalCount} = this.props;

    return (
      <InfiniteScroll
        loadMore={this.onLoadMore}
        hasMore={totalCount > data.length}
        loader={<div key={0}>Loading...</div>}
        useWindow={false}
      >
        {children}
      </InfiniteScroll>
    );
  }

}

export default InfiniteScrollComponent;