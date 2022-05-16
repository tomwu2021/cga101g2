package com.likelist.model;

public class LikelistService {

//	private:僅類別內有效
	private LikelistJDBCDAO dao;

//	改寫預設建構子
	public LikelistService() {
		dao = new LikelistJDBCDAO(); 
	}
	
	public boolean insertAndBoo(LikelistVO likelistVO) {
		return dao.insertAndBoo(likelistVO);
	}
	
	public boolean delete(LikelistVO likelistVO) {
		return dao.delete(likelistVO);
	}
	public LikelistVO getOneLikelistVOForCheck(Integer memberId, Integer postId) {
		return dao.getOneLikelistVOForCheck(memberId, postId);
	}
}
