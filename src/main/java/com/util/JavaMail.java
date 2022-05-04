package com.util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class JavaMail {
// ---------------------------------------------------------基本資料

	private String userName = "pclubpclub101@gmail.com"; // 寄件者信箱
	private String password = "P@ssW0rd"; // 寄件者密碼
	private String recipient; // 收件者郵箱
	private String subject = "Pclub 驗證碼"; // 標題
	private String txt; // 內容

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public void SendMail() {

// ---------------------------------------------------------連線設定
		Properties prop = new Properties();

		// 設定連線為smtp
		prop.setProperty("mail.transport.protocol", "smtp");

		// host主機:smtp.gmail.com
		prop.setProperty("mail.host", "smtp.gmail.com");

		// host port:465
		prop.setProperty("mail.smtp.port", "465");

		// 寄件者帳號需要驗證：是
		prop.setProperty("mail.smtp.auth", "true");

		// 需要安全資料傳輸層 (SSL)：是
		prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// 安全資料傳輸層 (SSL) 通訊埠：465
		prop.setProperty("mail.smtp.socketFactory.port", "465");

		// 顯示連線資訊
		prop.setProperty("mail.debug", "true");

		// ---------------------------------------------------------驗證
		// ---------------------------------------------------------Session默認屬性設定值
		// 匿名類別
//		Session session = Session.getDefaultInstance(prop, new Authenticator() {
//
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(userName, password);
//			}
//		});

		// class
		Auth auth = new Auth(userName, password);
		Session session = Session.getDefaultInstance(prop, auth);

		// ---------------------------------------------------------Message郵件格式
		MimeMessage message = new MimeMessage(session);

		try {
			// 寄件者
			// 匿名類別
			// message.setSender(new InternetAddress(userName));

			// class
			InternetAddress sender = new InternetAddress(userName);
			message.setSender(sender);

			// 收件者
			message.setRecipient(RecipientType.TO, new InternetAddress(recipient));

			// 標題
			message.setSubject(subject);

			// 內容/格式
			message.setContent(txt, "text/html;charset = UTF-8");

			// ---------------------------------------------------------Transport傳送Message
			Transport transport = session.getTransport();

			// transport將message送出
			transport.send(message);

			// 關閉Transport
			transport.close();

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class Auth extends Authenticator {

	private String userName;
	private String password;

	public Auth(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		PasswordAuthentication pa = new PasswordAuthentication(userName, password);
		return pa;
	}

}