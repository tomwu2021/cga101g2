package com.notification.model;

import java.util.List;
import com.notification.model.NotificationVO;

public interface NotificationDVO_interface {

	public void insert(NotificationVO notificationVO);

	public void update(NotificationVO notificationVO);

	public void delete(Integer notification_id);

	public NotificationVO findByPrimaryKey(Integer notification_id);

	public List<NotificationVO> getAll();

}