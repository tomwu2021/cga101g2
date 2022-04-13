package com.members.model;

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

	// select 情境九：管理員查詢某一筆會員資料 (透過 account)

	// select 情境十：管理員查詢所有會員資料

	// select 情境十一：管理員查詢所有被停權的所有會員 ( status )
}