package com.members.model;

import java.util.*;

import com.ranks.model.RanksVO;

public class MembersService {

	private MembersDAO_interface dao;

	public MembersService() {
		dao = new MembersDAO();
	}

	// insert 情境一：會員辦帳號時，輸入資料庫的內容
	public MembersVO insert(MembersVO membersVO) {
		return dao.insert(membersVO);
	}

	// update 情境二：會員可修改的資料 PASSWORD NAME ADDRESS PHONE E_WALLET_PASSWORD
	public MembersVO update(MembersVO membersVO) {
		return dao.update(membersVO);
	}

	// update 情境三：管理員可以修改此會員的狀態(停權/正常)
	public boolean changeStatus(MembersVO membersVO) {
		return dao.changeStatus(membersVO);
	}

	// update 情境四：管理員可以發送紅利
	public boolean changeBonus(MembersVO membersVO) {
		return dao.changeBonus(membersVO);
	}

	// update 情境五：會員忘記密碼 ( password )
	public String forgotPassword(MembersVO membersVO) {
		return dao.forgotPassword(membersVO);
	}

	// select 情境六：會員查詢會員等級 ( RANK_ID )
	public int selectRankId(MembersVO membersVO) {
		return dao.selectRankId(membersVO);
	}

	// select 情境七：會員查詢錢包餘額 ( E_WALLET_AMOUNT )
	public int selectEWalletAmount(MembersVO membersVO) {
		return dao.selectEWalletAmount(membersVO);
	}

	// select 情境八：會員查詢紅利帳戶 ( BONUS_AMOUNT )
	public int selectBonusAmount(MembersVO membersVO) {
		return dao.selectBonusAmount(membersVO);
	}

	// select 情境九：管理員使用 member_id 查詢某一筆會員資料
	public MembersVO getOneById(Integer id) {
		return dao.getOneById(id);
	}

	// select 情境十：管理員查詢所有會員資料
	public List<MembersVO> getAll() {
		return dao.getAll();
	}

	// select 情境十一：管理員查詢所有被停權的所有會員 ( status )
	public List<MembersVO> getAllStatus() {
		return dao.getAllStatus();
	}

	// select 情境十二：管理員使用 name 查詢某一筆會員資料
	public MembersVO getOneByName(String name) {
		return dao.getOneByName(name);
	}

	// select 情境十三：查詢登入時帳號和密碼
	public MembersVO selectForLogin(String name, String password) {
		return dao.selectForLogin(name, password);
	}

	// update 情境十四：會員儲值時，儲值紀錄改動，需要修改目前錢包餘額
	public boolean changeEWalletAmount(MembersVO membersVO) {
		return dao.changeEWalletAmount(membersVO);
	}

	// deleteOneById
	public boolean deleteOneById(Integer memberId) {
		return dao.deleteOneById(memberId);
	}

	// 查詢此帳號是否存在
	public Boolean getOneByAccount(String account) {
		return dao.getOneByAccount(account);
	}

	// 驗證碼
	public String genAuthCode() {
		return dao.genAuthCode();
	}

	// 查詢此帳號的資料
	public MembersVO selectMemberIdByAccount(String account) {
		return dao.selectMemberIdByAccount(account);
	}

	// 用 memberId 查 Rank 資訊
	public RanksVO selectRankInfo(Integer memberId) {
		return dao.selectRankInfo(memberId);
	}

	// 會員錢包 消費/儲值
	public boolean walletPaymentAddMoney(Integer memberId, Integer money) {
		return dao.walletPaymentAddMoney(memberId, money);
	}

}
