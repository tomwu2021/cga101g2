package com.group_buyer.model;

public class TestGroupBuyer {
	public static void main(String[] args) {
		//新增參團者
//		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
//		GroupBuyerVO groupBuyerVO=new GroupBuyerVO();
//		groupBuyerVO.setGroupOrderId(1);
//		groupBuyerVO.setRecipients("佩佩豬媽媽");
//		groupBuyerVO.setPhone("0999887766");
//		groupBuyerVO.setAddress("台中屠宰場");
//		groupBuyerVO.setMemberId(1);
//		groupBuyerVO.setProductAmount(10);
//		dao.insert(groupBuyerVO);
		
		//查詢所有團購成員
//		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
//		for(GroupBuyerVO a:dao.getAll()) {
//			System.out.println(a);
//		}
		
		//會員找所有參加的團購
//		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
//		for(GroupBuyerVO a:dao.getAllByMemberId(1)) {
//			System.out.println(a);
//		}
		
		//查詢單筆團購訂單所有成員
//		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
//		for(GroupBuyerVO a:dao.getAllByGroupOrderId(1)) {
//			System.out.println(a);
//		}
		
		//刪除訂單
//		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
//		dao.deleteByPK(2,1);
		
		//更改資料
		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
		GroupBuyerVO groupBuyerVO=new GroupBuyerVO();
		groupBuyerVO.setGroupOrderId(1);
//		groupBuyerVO.setRecipients("維尼熊雄");
		groupBuyerVO.setPhone("0988765684");
		groupBuyerVO.setAddress("台南屠宰場");
		groupBuyerVO.setMemberId(1);
		dao.update(groupBuyerVO);
		

	}
}
