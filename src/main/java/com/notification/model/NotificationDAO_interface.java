package com.notification.model;

import java.util.List;
import com.common.model.JDBCDAO_Interface;

public interface NotificationDAO_interface extends JDBCDAO_Interface<NotificationVO> {

	List<NotificationVO> getAllById(Integer id);
	
	public Boolean updateStatus(NotificationVO notificationVO);

}