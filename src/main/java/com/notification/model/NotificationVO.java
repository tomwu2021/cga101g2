package com.notification.model;

import java.sql.Timestamp;

public class NotificationVO implements java.io.Serializable {

	private Integer notificationId;
	private Integer memberId;
	private String context;
	private Timestamp time;
	private Integer status;

	public NotificationVO() {
	}

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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

	@Override
	public String toString() {
		return "NotificationVO [notificationId=" + notificationId + ", memberId=" + memberId + ", context=" + context
				+ ", time=" + time + ", status=" + status + "]";
	}

}
