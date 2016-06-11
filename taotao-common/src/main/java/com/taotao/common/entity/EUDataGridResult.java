package com.taotao.common.entity;

import java.util.List;

/**
 *easyUI分页控件
 */
public class EUDataGridResult {

	private long total;
	
	private List<?> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
