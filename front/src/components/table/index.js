import React from 'react';
import ReactTable from 'react-table';
import 'react-table/react-table.css';
import './index.scss';

export const Table = ({columns, data}) => {
	return (
		<div>
			<ReactTable
				data={data}
				columns={columns}
				className='custom-table'
				sortable={false}
				showPagination={false}
				minRows={0}
				resizable={false}
			/>
		</div>
	)
}
