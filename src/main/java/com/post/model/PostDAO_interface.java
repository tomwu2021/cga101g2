package com.post.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;
import com.members.model.MembersVO;

public interface PostDAO_interface extends JDBCDAO_Interface<PostVO> {

	// 查看個人頁面（個人貼文）
	public List<PostVO> selectPost(Integer id);

	// 查看熱門貼文
	public List<PostVO> selectHotPost();

	// 查詢貼文，顯示 status狀態0:正常1:審核中2:刪除
	public List<PostVO> selectChangePost();

	// 刪除貼文
	public boolean updatedelete(Integer postId);

	public PostVO getOneById(Integer postId, Integer memberId);

	// 查詢單篇貼文讚數
	int selectOnePostLikeCount(Integer postId);
	
	// 更新單篇貼文讚數
	//同時新增likelistVO跟單篇貼文讚數 (實務上並不常用, 一次新增成功)
	int updateOnePostLikeCount(Integer newLikeCount, Integer postId, java.sql.Connection con);
	
	//讀取會員頭貼跟姓名
	public MembersVO selectmember(Integer memberId);
	
	//搜尋貼文關鍵字
	public List<PostVO> selectkeyword(String content);
	
	public boolean updateReport(Integer postId);

	boolean updateNormal(Integer postId);

	PostVO getOneByReport(Integer postId, Integer memberId);

}
