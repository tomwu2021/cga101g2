package com.report.model;

import java.sql.Date;

public class ReportVO implements java.io.Serializable {
	
	private Integer reportId;
	private Integer reporterId;	
	private Integer postId;	
	private String reportReason;
	private Date reportTime;	
	private Integer status;
	private String url;
	
	public ReportVO() {
		super();
	}

	public ReportVO(Integer report_id, Integer reporter_id, Integer post_id, String report_reason, Date report_time,
			Integer status) {
		super();
		this.reportId = report_id;
		this.reporterId = reporter_id;
		this.postId = post_id;
		this.reportReason = report_reason;
		this.reportTime = report_time;
		this.status = status;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer report_id) {
		this.reportId = report_id;
	}

	public Integer getReporterId() {
		return reporterId;
	}

	public void setReporterId(Integer reporter_id) {
		this.reporterId = reporter_id;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer post_id) {
		this.postId = post_id;
	}

	public String getReportReason() {
		return reportReason;
	}

	public void setReportReason(String report_reason) {
		this.reportReason = report_reason;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date report_time) {
		this.reportTime = report_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
	
	
	
}


