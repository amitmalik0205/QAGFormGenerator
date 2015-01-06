package com.qait.qag.formgenerator.simpletemplate.domain;

import java.util.ArrayList;
import java.util.List;

public class PageDetail {
	
	private int pageNo;
	
	private List<ColumnDetail> columnList = new ArrayList<ColumnDetail>();

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<ColumnDetail> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<ColumnDetail> columnList) {
		this.columnList = columnList;
	}
}
