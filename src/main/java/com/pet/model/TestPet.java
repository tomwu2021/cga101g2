package com.pet.model;

import java.sql.Date;
import java.util.List;

import com.pet.service.PetService;


public class TestPet {
	
	public static void main(String[] args) {

		PetJDBCDAO dao = new PetJDBCDAO();
		
		PetService svc = new PetService();
		svc.defaultPet(37);
		
		// 新增
//		PetVO pVO1 = new PetVO();
//		pVO1.setMemberId(1);
//		pVO1.setPetName("白白 ");
//		pVO1.setSort1Id(1);
//		pVO1.setGender(null);
//		pVO1.setIntroduction(null);
//		pVO1.setPictureId(null);
//		Object birthday = "2011-04-10";//也可null
//		if(birthday != null) birthday = Date.valueOf((String) birthday);
//		pVO1.setBirthday((Date)birthday);
//		System.out.println(dao.insert(pVO1));
//		
		// 刪除
//		PetVO pVO2 = new PetVO();
//		pVO2.setPetId(4);
//		System.out.println(dao.delete(pVO2));
		
		// 修改資料
//		PetVO pVO3 = new PetVO();
//		pVO3.setPetName("Alex");
//		pVO3.setSort1Id(1);
//		pVO3.setGender(0);
//		pVO3.setIntroduction("OHIYO");
//		pVO3.setPictureId(1);
//		Object birthdayU = "2011-04-10";//也可null
//		if(birthday != null) birthdayU = Date.valueOf((String) birthday);
//		pVO3.setBirthday((Date)birthdayU);
//		pVO3.setPetId(9);
//		System.out.println(dao.update(pVO3));
		
		// 修改公開/隱藏狀態
//		System.out.println(dao.changeStatus(1, 0));

		// 以寵物ID查詢
//		System.out.println(dao.getOneById(8));

		// 以會員ID查詢
//		System.out.println(dao.getOneByMemberId(4));

		// 查全部
//		List<PetVO> pList = dao.getAll();
//		for(PetVO pVO:pList) {
//		System.out.println(pVO);
//		}
		
		// 查公開且該月生日的寵物
//		List<PetVO> bList = dao.getAllByBirth(12);
//		for(PetVO pVO:bList) {
//		System.out.println(pVO);
//		}
	}

}
