package com.notification.model;

public class TestNotificationDAO {

	public static void main(String[] args) {

		NotificationJDBCDAO dao = new NotificationJDBCDAO();

		// 情境一 insert：新增一筆通知
		NotificationVO notificationVO1 = new NotificationVO();
		notificationVO1.setMemberId(3); 
		notificationVO1.setContext("國此，三遊山川明朝輪臺。青臨洮芙蓉⋯殷勤城臺城何事之然不得，秋青天蹉跎，一身萋萋太故人無情，道之難窮啼風波，難難於關顏色二一。");
		notificationVO1.setUrl("https://www.google.com.tw");
		System.out.println(dao.insert(notificationVO1));

		// 情境二 select：查詢某會員所有通知
//		for (NotificationVO n : dao.getAllById(3)) {
//			System.out.println(n);
//		}

		// 情境三 update：會員查看通知後，status 變成已讀 1
//		NotificationVO notificationVO2 = new NotificationVO();
//		notificationVO2.setNotificationId(3);
//		System.out.println(dao.updateStatus(notificationVO2));

	}

}
