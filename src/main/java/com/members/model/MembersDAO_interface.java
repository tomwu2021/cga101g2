package com.members.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;

public interface MembersDAO_interface extends JDBCDAO_Interface<MembersVO> {

	// update 情境三：管理員可以修改此會員的狀態(停權/正常)
	public boolean changeStatus(MembersVO membersVO);

	// update 情境四：管理員可以發送紅利
	public boolean changeBonus(MembersVO membersVO);

	// update 情境五：會員忘記密碼 ( password )
	public boolean forgotPassword(MembersVO membersVO);

	// select 情境六：會員查詢會員等級 ( RANK_ID )
	public int selectRankId(MembersVO membersVO);

	// select 情境七：會員查詢錢包餘額 ( E_WALLET_AMOUNT )
	public int selectEWalletAmount(MembersVO membersVO);
	
	// select 情境八：會員查詢紅利帳戶 ( BONUS_AMOUNT )
	public int selectBonusAmount(MembersVO membersVO);

	// select 情境十一：管理員查詢所有被停權的所有會員 ( status )
	public List<MembersVO> getAllStatus();
	
	// select 情境十二：管理員使用 name 查詢某一筆會員資料
	public MembersVO getOneByName(String name);
	
	// select 情境十三：查詢登入時帳號和密碼
	public MembersVO selectForLogin(String name, String password);
	
}