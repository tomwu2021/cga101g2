package com.members.model;

import java.util.List;

import com.amazonaws.services.s3.model.PublicAccessBlockConfiguration;
import com.common.model.JDBCDAO_Interface;
import com.pet.model.PetVO;
import com.ranks.model.RanksVO;

public interface MembersDAO_interface extends JDBCDAO_Interface<MembersVO> {

	// update 情境三：管理員可以修改此會員的狀態(停權/正常)
	public boolean changeStatus(MembersVO membersVO);

	// update 情境四：管理員可以發送紅利
	public boolean changeBonus(MembersVO membersVO);

	// update 情境五：會員忘記密碼 ( password )
	public String forgotPassword(MembersVO membersVO);

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

	// update 情境十四：會員儲值時，儲值紀錄改動，需要修改目前錢包餘額
	public boolean changeEWalletAmount(MembersVO membersVO);

	// delete
	public boolean deleteOneById(Integer memberId);

	// 判斷此帳號在資料庫使否有資料
	public boolean getOneByAccount(String account);

	// 驗證碼
	public String genAuthCode();

	// 用 account 查詢 id
	public MembersVO selectMemberIdByAccount(String account);

	// 用 memberId 查詢 rank 資訊
	public RanksVO selectRankInfo(Integer memberId);

	// 會員錢包 消費/儲值
	public boolean walletPaymentAddMoney(Integer memberId, Integer money);

	// member_id 查詢某一筆會員資料
	public MembersVO getOneById(Integer id);
	
	// 紅利 消費/發送
	public boolean bonusPaymentAddValue(Integer memberId,Integer bonus);
	
	// 用 memberId 取得 eWalletPassword
	public String geteWalletPassword(Integer memberId);
	
	// 修改會員等級
	public Boolean updateRank(Integer memberId,Integer rankId);
	
	// 用帳號模糊查詢
	public List<MembersVO> SelectAllByAccount(String account);
	
	// 用姓名模糊查詢
	public List<MembersVO> SelectAllByName(String name);
	
}