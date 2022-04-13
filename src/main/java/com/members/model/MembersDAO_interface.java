package com.members.model;
import com.common.model.JDBCDAO_Interface;

public interface MembersDAO_interface extends JDBCDAO_Interface<MembersVO>{
	
	// update 情境二：管理員可以修改此會員的狀態(停權/正常)
	public boolean changeStatus(MembersVO membersVO);

	// update 情境三：管理員可以發送紅利
	public boolean changeBonus(MembersVO membersVO);
	
	// update 情境四：會員忘記密碼 ( password )
	
	// select 情境一：會員查詢會員等級 ( RANK_ID )
	
	// select 情境二：會員查詢錢包餘額 ( E_WALLET_AMOUNT )
	
	// select 情境三：會員查詢紅利帳戶 ( BONUS_AMOUNT	 )
	
	// select 情境一：管理員查詢某一筆會員資料 (透過 account)
	
	// select 情境二：管理員查詢所有會員資料
	
	// select 情境三：管理員模糊查詢某地區的所有會員 ( address )
	
	// select 情境四：管理員查詢所有被停權的所有會員 ( status )
}