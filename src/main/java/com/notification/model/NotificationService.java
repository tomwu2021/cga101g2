package com.notification.model;

import java.util.*;

public class NotificationService {
	
	private NotificationDAO_interface dao;

	public NotificationService() {
		dao = new NotificationJDBCDAO();
	}

	// 情境一 insert：新增一筆通知
	public NotificationVO insert(NotificationVO notificationVO) {
		return dao.insert(notificationVO);
	}

	// 情境二 select：查詢某會員 id 所有通知
	public List<NotificationVO> getAllById(Integer id) {
		return dao.getAllById(id);
	}

	// 情境三 update：會員查看通知後，status 變成已讀 1
	public Boolean updateStatus(NotificationVO notificationVO) {
		return dao.updateStatus(notificationVO);
	}

}
