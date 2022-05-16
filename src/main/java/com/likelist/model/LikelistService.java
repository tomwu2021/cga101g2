package com.likelist.model;

public class LikelistService {

//	private:僅類別內有效
	private LikelistJDBCDAO dao;

//	改寫預設建構子
	public LikelistService() {
		dao = new LikelistJDBCDAO();
	}

	// 新增按讚
	public boolean insertAndBoo(LikelistVO likelistVO, Integer newLikeCount, Integer postId) {
		return dao.insertAndBoo(likelistVO, newLikeCount, postId);
	}

	// 刪除按讚（收回讚）
	public boolean delete(LikelistVO likelistVO, Integer newLikeCount, Integer postId) {
		return dao.delete(likelistVO, newLikeCount, postId);
	}

	public LikelistVO getOneLikelistVOForCheck(Integer memberId, Integer postId) {
		return dao.getOneLikelistVOForCheck(memberId, postId);
	}
}
