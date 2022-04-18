package com.common.model;

import java.sql.Timestamp;
import java.util.Map;

public class PageQuery {

	private Integer thisPage;
	private Integer pageSize;
	private Integer start;
	private Integer end;
	private String sort = "ASC";
	private String order;
	private Map<String, Object> map;
	private String whereSQL = "";

	// 查詢條件 1.file_name 2.member_id

	public void buildWhereSQL() {
		String sql = "";
		for (String column : map.keySet()) {
			Object value = map.get(column);
			if (value == null || (value instanceof String && "".equals((String) value))) {
				continue;
			}
			if ("".equals(sql)) {
				sql += " WHERE " + column;
				if (value instanceof Integer) {
					sql += " = " + (Integer) value;
				} else if (value instanceof String) {
					sql += " LIKE '%" + (String) value + "%' ";
				}
			} else {
				sql += " AND " + column;
				if (value instanceof Integer) {
					sql += " = " + (Integer) value;
				} else if (value instanceof String) {
					sql += " LIKE '%" + (String) value + "%' ";
				}
			}
		}
		this.whereSQL = sql;
	}
	/**
	 * 生成PageQuery物件 
	 * @param thisPage 當前頁數 
	 * @param pageSize 顯示筆數
	 * @param sort	排序方式
	 * @param order	排序欄位
	 * @param map 	查詢條件 Map<欄位名稱, 條件值>
	 */
	public PageQuery(Integer thisPage, Integer pageSize, String sort, String order, Map<String, Object> map) {
		super();
		if (thisPage < 1 || pageSize < 1) {
			this.thisPage = 1;
			this.pageSize = 1;
		}
		this.thisPage = thisPage;
		this.pageSize = pageSize;
		this.sort = sort;
		this.order = order;
		this.map = map;
		setStart(((thisPage - 1) * pageSize) + 1);
		setEnd(thisPage * pageSize);
		buildWhereSQL();
	}

	public PageQuery() {
		super();
	}

	public Integer getLimitStart() {
		return this.start - 1;
	}

	public Integer getLimitEnd() {
		return pageSize;
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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getWhereSQL() {
		return whereSQL;
	}

	public void setWhereSQL(String whereSQL) {
		this.whereSQL = whereSQL;
	}

	public String getOrderBySql() {
		if (this.order == null || "".equals(this.order)) {
			return "";
		}
		if (this.sort != null && !("".equals(this.sort)) && "DESC".equals(this.sort.toUpperCase())) {
			return " ORDER BY " + this.order + " DESC ";
		} else {
			return " ORDER BY " + this.order + " ASC ";
		}

	}

	/**
	 * 設置時間條件大於指定欄位
	 * 
	 * @param column 欄位名稱
	 * @param time   指定時間
	 */
	public void setFindByAfterTime(String column, Timestamp time) {
		String sql = this.whereSQL;
		if ("".equals(sql)) {
			sql += " WHERE " + column + " > '" + time + "' ";
		} else {
			sql += " AND " + column + " > '" + time + "' ";
		}
		this.whereSQL = sql;
	}

	/**
	 * 設置時間條件小於指定欄位
	 * 
	 * @param column 欄位名稱
	 * @param time   指定時間
	 */
	public void setFindByBeforeTime(String column, Timestamp time) {
		String sql = this.whereSQL;
		if ("".equals(sql)) {
			sql += " WHERE " + column + " < '" + time + "' ";
		} else {
			sql += " AND " + column + " < '" + time + "' ";
		}
		this.whereSQL = sql;
	}

	/**
	 * 設置時間條件BETWEEN指定欄位
	 * 
	 * @param column    欄位名稱
	 * @param startTime 起始時間
	 * @param endTime   結束時間
	 */
	public void setFindByBetweenTime(String column, Timestamp startTime, Timestamp endTime) {
		String sql = this.whereSQL;
		if ("".equals(sql)) {
			sql += " WHERE " + column + " BETWEEN '" + startTime + "' AND '" + endTime + "' ";
		} else {
			sql += " AND " + column + " BETWEEN '" + startTime + "' AND '" + endTime + "' ";
		}
		this.whereSQL = sql;
	}

	/**
	 * 取得組合好之查詢SQL指令
	 * 
	 * @param sql 查詢SQL指令
	 * @return
	 */
	public String getQuerySQL(String sql) {
		String querySql = sql + this.whereSQL + this.getOrderBySql() + " LIMIT ?,? ";
		System.out.println(querySql);
		return querySql;
	}

	public String getTotalCountSql(String sql) {
		return sql + this.whereSQL;
	}

	@Override
	public String toString() {
		return "PageQuery:{thisPage:" + thisPage + ", pageSize:" + pageSize + ", start:" + start + ", end:" + end
				+ ",}";
	}

}
