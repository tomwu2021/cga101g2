package com.common.model;

import com.sun.istack.NotNull;

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

	public PageQuery() {
		super();
	}

	/**
	 * 生成PageQuery物件
	 * @param thisPage 當前頁數
	 * @param pageSize 顯示筆數
	 * @param sort	排序方式
	 * @param order	排序欄位
	 * @param map 	查詢條件 Map<欄位名稱, 條件值>
	 */
	public PageQuery(@NotNull Integer thisPage,@NotNull Integer pageSize, String sort, String order, Map<String, Object> map) {
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

	//從controller呼叫建構子，傳入參數時會被呼叫，呼叫後取得map<欄位,條件值>生成WHERE條件句(時間條件於其他方法生成)
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
	 * 設置時間條件大於指定欄位,
	 * 形成WHERE 時間條件指令
	 * @param column 欄位名稱
	 * @param time   指定時間
	 */
	//時間條件值來自controller
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
	 * 設置時間條件小於指定欄位,
	 * 形成WHERE 時間條件指令
	 * @param column 欄位名稱
	 * @param time   指定時間
	 */
	//時間條件值來自controller
	public void setFindByBeforeTime(String column, Timestamp time) {
		String sql = this.whereSQL;
		if ("".equals(sql)) {
			sql += " WHERE " + column + " < '" + time + "' ";
		} else {
			sql += " AND " + column + " < '" + time + "' ";
		}
		this.whereSQL = sql;
	}

	/**形成WHERE 時間條件指令
	 * 設置指定欄位之時間區間(BETWEEN)條件
	 *
	 * @param column    欄位名稱
	 * @param startTime 起始時間
	 * @param endTime   結束時間
	 */
	//時間條件值來自controller
	public void setFindByBetweenTime(String column, Timestamp startTime, Timestamp endTime) {
		String sql = this.whereSQL;
		if ("".equals(sql)) {
			sql += " WHERE " + column + " BETWEEN '" + startTime + "' AND '" + endTime + "' ";
		} else {
			sql += " AND " + column + " BETWEEN '" + startTime + "' AND '" + endTime + "' ";
		}
		this.whereSQL = sql;
	}

	//取得來自前端order排序條件 與 升冪降冪 形成ORDER BY指令
	public String getOrderBySQL() {
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
	 * 取得組合好之查詢SQL指令
	 * @param baseSQL 基本查詢SQL指令(包含FROM 或加上JOIN語法), 不含條件(WHERE)語句. e.g: 'SELECT * FROM demo'
	 * @return 組合好查詢條件SQL
	 */
	//baseSQL來自DAO 事先寫好的SQL指令基底，組裝完成後，Limit 參數由各DAO實作
	public String getQuerySQL(@NotNull String baseSQL) {
		String querySQL = baseSQL + this.whereSQL + this.getOrderBySQL() + " LIMIT ?,? ";
		querySQL = querySQL.replaceFirst("\\?", this.getLimitStart().toString());
		querySQL = querySQL.replaceFirst("\\?", this.getLimitEnd().toString());
		System.out.println("querySQL> " + querySQL);
		return querySQL;
	}

	// countSQL來自DAO，取得計算總筆數的sql指令(條件須與取回資料之SQL指令相同,總筆數才會一致)
	/**
	 * 取得相同條件下,總筆數之SQL語法
	 * @param baseSQL 基本查詢SQL指令(包含FROM 或加上JOIN語法), 不含條件(WHERE)語句. e.g: 'SELECT * FROM demo'
	 * @return 組合好查詢總筆數條件SQL
	 */
	public String getTotalCountSQL(@NotNull String baseSQL) {
		int getFrom = baseSQL.indexOf("FROM");
		if(getFrom < 0){
			getFrom = baseSQL.indexOf("from");
		}
		String selectCountSQL = "SELECT COUNT(*) " + baseSQL.substring( getFrom) + this.whereSQL;
		System.out.println("selectCountSQL> " + selectCountSQL);
		return selectCountSQL ;
	}


	@Override
	public String toString() {
		return "PageQuery:{thisPage:" + thisPage + ", pageSize:" + pageSize + ", start:" + start + ", end:" + end
				+ ",}";
	}

}
