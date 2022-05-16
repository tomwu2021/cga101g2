package com.members.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.pet.model.PetVO;


@WebServlet("/BounsController")
public class BounsController extends HttpServlet implements Runnable {
	private static final long serialVersionUID = 1L;
	ScheduledExecutorService service = null;

	public void run() {
		new BounsController();
	}

	public void init(ServletConfig config) throws ServletException {
		service = Executors.newScheduledThreadPool(10);
		long initialDelay = 10;
		long period = 10;
		// 從現在開始1秒鐘之後，每隔1秒鐘執行一次job1
		service.scheduleAtFixedRate(new BounsController(), initialDelay, period, TimeUnit.SECONDS);

	}
	
	public BounsController() {
		super();
		System.out.println("紅利排程器啟動中");
		
		// 生日發送紅利
		MembersService memberSvc = new MembersService();
		List<PetVO>  AllpetVO = memberSvc.getAllPetBirthday();
		System.out.println(AllpetVO);
		
	}

	public void destroy() {
		service.shutdown();
	}
	


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
}
