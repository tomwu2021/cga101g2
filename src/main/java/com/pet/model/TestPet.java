package com.pet.model;

import java.sql.Date;
import java.util.List;


public class TestPet {
	
	public static void main(String[] args) {

		PetJDBCDAO dao = new PetJDBCDAO();
		
		// 新增
		PetVO pVO1 = new PetVO();
		pVO1.setMemberId(1);
		pVO1.setPetName("小小");
		pVO1.setType(1);
		pVO1.setGender(1);
		pVO1.setIntroduction("哈哈哈");
		pVO1.setPictureId(1);
		pVO1.setBirthday(Date.valueOf("2011-04-10"));
		System.out.println(dao.insert(pVO1));
		
		// 刪除
		PetVO pVO2 = new PetVO();
		pVO2.setPetId(4);
		System.out.println(dao.delete(pVO2));
		
		// 修改
		PetVO pVO3 = new PetVO();
		pVO3.setPetName("Alex");
		pVO3.setType(1);
		pVO3.setGender(0);
		pVO3.setIntroduction("OHIYO");
		pVO3.setPictureId(1);
		pVO3.setBirthday(Date.valueOf("2011-04-16"));
		pVO3.setStatus(0);
		pVO3.setPetId(1);
		System.out.println(dao.update(pVO3));

		// 查一筆
		System.out.println(dao.getOneById(5));

		// 查全部
		List<PetVO> plist = dao.getAll();
		
		for(PetVO pVO:plist) {
			System.out.println(pVO);
		}
	}


}
