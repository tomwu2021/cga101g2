package com.members.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.plaf.synth.SynthGraphicsUtils;
import javax.xml.transform.Source;

import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.pet.model.PetDAO;
import com.pet.model.PetVO;
import com.pet.service.PetService;

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
		// 從現在開始1秒鐘之後，每隔1天執行一次job1
		service.scheduleAtFixedRate(new BounsController(), initialDelay, period, TimeUnit.DAYS);

	}

	public BounsController() {
		super();
		System.out.println("紅利排程器啟動中");

		// Service
		PetService petSvc = new PetService();
		MembersService memberSvc = new MembersService();

		// 取得當月生日的人
		Calendar cal = Calendar.getInstance();
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
//		System.out.println(year);
//		System.out.println(month);
//		System.out.println(day);

		List<PetVO> listPetVO = petSvc.getByBirthday(1); // 找出所有當月生日的人，測試完 1 要改成 month
		for (PetVO petVO : listPetVO) {

			// 取得當月寵物的生日是第幾天
			String dateToStr = String.format("%1$tY-%1$tm-%1$td", petVO.getBirthday());

			// 取得日期
			String petBirthday = dateToStr.substring(8, 10);
//			 日期比對
			if (String.valueOf(day).equals(petBirthday)) { 
//			if ("01".equals(petBirthday)) { // 測試完 "01" 要改成 day
				System.out.println("日期正確，發送紅利");
				// 依照會員等級發送紅利，用 memberId 取得會員等級
				MembersVO membersVO = memberSvc.getOneById(petVO.getMemberId());
				Integer rank = membersVO.getRankId();
				// 發送紅利
				MembersVO bonusMembersVO = new MembersVO();
				bonusMembersVO.setMemberId(petVO.getMemberId());
				System.out.println(petVO.getMemberId());

				switch (rank) {
				case 1: 
					bonusMembersVO.setBonusAmount(50);
					break;
				case 2: 
					bonusMembersVO.setBonusAmount(100);
					break;
				case 3: 
					bonusMembersVO.setBonusAmount(150);
					break;
				case 4: 
					bonusMembersVO.setBonusAmount(200);
					break;
				}
				memberSvc.changeBonus(bonusMembersVO);
			}
		}
	}

	public void destroy() {
		service.shutdown();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
