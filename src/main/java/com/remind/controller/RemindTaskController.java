package com.remind.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.members.model.*;
import com.remind.model.RemindVO;
import com.remind.service.RemindService;
import com.util.JavaMail;

public class RemindTaskController extends HttpServlet implements Runnable {
	private static final long serialVersionUID = 1L;
	ScheduledExecutorService scheduleSvc = null;
	RemindService RemindSvc = new RemindService();
	MembersService membersSvc = new MembersService();

	@Override
	public void run() {
		new RemindTaskController(0);
	}

	public void init(ServletConfig config) throws ServletException {
		scheduleSvc = Executors.newScheduledThreadPool(10);
		long oneHour = 1000 * 60 * 60;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, 55);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        cal.add(Calendar.HOUR,0);
		long initialDelay = cal.getTime().getTime() - System.currentTimeMillis();
		scheduleSvc.scheduleAtFixedRate(new RemindTaskController(), initialDelay, oneHour, TimeUnit.MILLISECONDS);

	}
	public void destroy() {
		scheduleSvc.shutdown();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public RemindTaskController() {
		
	}
	
	
	public RemindTaskController(int i) {
		List<RemindVO> rVOs = RemindSvc.getRecentRemind();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(RemindVO rVO : rVOs) {
			MembersVO mVO = membersSvc.getOneById(rVO.getMemberId());
			JavaMail javaMail = new JavaMail();
			javaMail.setRecipient(mVO.getAccount());
			javaMail.setSubject("PCLUB提醒通知："+rVO.getContent()+"-"+dateFormat.format(rVO.getTime()));
			javaMail.setTxt("親愛的<b>"+mVO.getName()+"</b>小姐/先生 您好，<br/>提醒您：<br/>"+rVO.getContent()+"即將於"+dateFormat.format(rVO.getTime())+"開始！");
			javaMail.SendMail(); 
		}
	}

}
