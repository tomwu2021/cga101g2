package com.notification.model;

import java.sql.Timestamp;

public class NotificationVO implements java.io.Serializable {
	private Integer notification_id;
	private Integer member_id;
	private String context;
	private Timestamp time;
	private Integer status;

	public NotificationVO() {
	}

	public Integer getNotification_id() {
		return notification_id;
	}

	public void setNotification_id(Integer notification_id) {
		this.notification_id = notification_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
