package com.members.controller;

import java.io.IOException;
import java.util.Calendar;
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
import com.notification.model.NotificationService;
import com.notification.model.NotificationVO;
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
		service.scheduleAtFixedRate(new BounsController(), initialDelay, period, TimeUnit.MINUTES);

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
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
//		System.out.println(year);
//		System.out.println(month);
//		System.out.println(day);

		List<PetVO> listPetVO = petSvc.getByBirthday(month); // 找出所有當月生日的人，測試完 1 要改成 month
		for (PetVO petVO : listPetVO) {
			if (petVO.getBirthday() == null) {
				continue;
			} else {
				// 取得當月寵物的生日是第幾天
				String dateToStr = String.format("%1$tY-%1$tm-%1$td", petVO.getBirthday());

				// 取得日期
				String petBirthday = dateToStr.substring(8, 10);
//				 日期比對
				System.out.println(petVO);
				System.out.println(String.valueOf(day));
				System.out.println("pet：" + dateToStr);
				System.out.println("pet：" + petBirthday);
				if (String.valueOf(day).equals(petBirthday)) {

//				if (day.equals(petBirthday)) { // 測試完 "01" 要改成 day
					System.out.println("日期正確，發送紅利");
					// 依照會員等級發送紅利，用 memberId 取得會員等級
					MembersVO membersVO = memberSvc.getOneById(petVO.getMemberId());
					String rank = String.valueOf(membersVO.getRankId());
					// 發送紅利
					MembersVO bonusMembersVO = new MembersVO();
					bonusMembersVO.setMemberId(petVO.getMemberId());
					System.out.println(petVO.getMemberId());
					System.out.println("發送紅利");

					NotificationService notificationSvc = new NotificationService();
					NotificationVO notificationVO = new NotificationVO();

					System.out.println(rank);
					switch (rank) {
					case "1":
						// 發送紅利通知
						notificationVO.setMemberId(petVO.getMemberId());
						notificationVO.setContext("恭喜您獲得生日紅利點數回饋 50 點！");
						notificationVO.setUrl("/CGA101G2/account.jsp");
						notificationSvc.insert(notificationVO);
						bonusMembersVO.setBonusAmount(50);
						memberSvc.changeBonus(bonusMembersVO);
						System.out.println("發送紅利 50 ");
						break;
					case "2":
						// 發送紅利通知
						notificationVO.setMemberId(petVO.getMemberId());
						notificationVO.setContext("恭喜您獲得生日紅利點數回饋 100  點！");
						notificationVO.setUrl("/CGA101G2/account.jsp");
						notificationSvc.insert(notificationVO);
						bonusMembersVO.setBonusAmount(100);
						memberSvc.changeBonus(bonusMembersVO);
						System.out.println("發送紅利 100 ");
						break;
					case "3":
						// 發送紅利通知
						notificationVO.setMemberId(petVO.getMemberId());
						notificationVO.setContext("恭喜您獲得生日紅利點數回饋 200 點！");
						notificationVO.setUrl("/CGA101G2/account.jsp");
						notificationSvc.insert(notificationVO);
						bonusMembersVO.setBonusAmount(200);
						memberSvc.changeBonus(bonusMembersVO);
						System.out.println("發送紅利 200 ");
						break;
					case "4":
						// 發送紅利通知
						notificationVO.setMemberId(petVO.getMemberId());
						notificationVO.setContext("恭喜您獲得生日紅利點數回饋 350 點！");
						notificationVO.setUrl("/CGA101G2/account.jsp");
						notificationSvc.insert(notificationVO);
						bonusMembersVO.setBonusAmount(200);
						memberSvc.changeBonus(bonusMembersVO);
						System.out.println("發送紅利 350 ");
						break;
					}

				}
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
