package com.common.model;

import java.util.List;

public class PageResult<T> {

	private Integer thisPage;
	private Integer pageSize;
	private Integer start;
	private Integer end;
	private List<T> items;
	private Integer pageCount;
	private Integer total;

	public PageResult() {
	}

	public PageResult(PageQuery pageQuery, List<T> items, Integer total) {
		super();
		this.thisPage = pageQuery.getThisPage();
		this.pageSize = pageQuery.getPageSize();
		this.start = pageQuery.getStart();
		this.total = total;
		if (pageQuery.getEnd() > total) {
			this.end = total;
		} else {
			this.end = pageQuery.getEnd();
		}
		this.items = items;
		if(total == 0) {
			this.start = 0;
			this.pageCount = 1;
		}else if (total!=0 && total % this.pageSize == 0) {
			this.pageCount = total / this.pageSize;
		} else {
			this.pageCount = total / this.pageSize + 1;
		}
	}

	public Integer getThisPage() {
		return thisPage;
	}

	public void setThisPage(Integer thisPage) {
		this.thisPage = thisPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "PageResult:{thisPage:" + thisPage + ", pageSize:" + pageSize + ", start:" + start + ", end:" + end
				+ ", items:" + items + ", pageCount:" + pageCount + ", total:" + total + "}";
	}

}
