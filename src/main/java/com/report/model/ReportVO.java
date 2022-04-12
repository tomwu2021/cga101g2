package com.report.model;

import java.sql.Date;

public class ReportVO implements java.io.Serializable {
	
	private Integer report_id;
	private Integer reporter_id;	
	private Integer post_id;	
	private String  report_reason;
	private Date report_time;	
	private Integer status;
	
	public ReportVO() {
		super();
	}

	public ReportVO(Integer report_id, Integer reporter_id, Integer post_id, String report_reason, Date report_time,
			Integer status) {
		super();
		this.report_id = report_id;
		this.reporter_id = reporter_id;
		this.post_id = post_id;
		this.report_reason = report_reason;
		this.report_time = report_time;
		this.status = status;
	}

	public Integer getReport_id() {
		return report_id;
	}

	public void setReport_id(Integer report_id) {
		this.report_id = report_id;
	}

	public Integer getReporter_id() {
		return reporter_id;
	}

	public void setReporter_id(Integer reporter_id) {
		this.reporter_id = reporter_id;
	}

	public Integer getPost_id() {
		return post_id;
	}

	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}

	public String getReport_reason() {
		return report_reason;
	}

	public void setReport_reason(String report_reason) {
		this.report_reason = report_reason;
	}

	public Date getReport_time() {
		return report_time;
	}

	public void setReport_time(Date report_time) {
		this.report_time = report_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}	
	
}


