package com.post.model;

import java.sql.Date;
import java.util.List;

import com.members.model.MembersVO;
import com.pet.model.PetVO;
import com.picture.model.PictureVO;
import com.likelist.model.LikelistVO;

public class PostVO implements java.io.Serializable{
	private Integer postId;
	private Integer memberId;
	private MembersVO membersVO;
	private List<String> urlList;
	private String content;	
	private Integer likeCount;
	private Integer status;	
	private Integer authority;
	private Date createTime;	
	private Date updateTime;
	private List<PictureVO> pictureList;
	private PictureVO pictureVO;        //頭貼照片
	private PictureVO pictureVO2;		//貼文照片
	
	private LikelistVO likelistVO;		//按讚清單
	

	public LikelistVO getLikelistVO() {
		return likelistVO;
	}

	public void setLikelistVO(LikelistVO likelistVO) {
		this.likelistVO = likelistVO;
	}

	public PostVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostVO(Integer postId, Integer memberId, String content, Integer likeCount, Integer status,
			Integer authority, Date createTime, Date updateTime, List<PictureVO> pictureList) {
		super();
		this.postId = postId;
		this.memberId = memberId;
		this.content = content;
		this.likeCount = likeCount;
		this.status = status;
		this.authority = authority;
		this.createTime = createTime;
		this.updateTime = updateTime;
		
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	public List<PictureVO> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<PictureVO> pictureList) {
		this.pictureList = pictureList;
	}
	
	public MembersVO getMembersVO() {
		return membersVO;
	}

	public void setMembersVO(MembersVO membersVO) {
		this.membersVO = membersVO;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	@Override
	public String toString() {
		return "PostVO [postId=" + postId + ", memberId=" + memberId + ", content=" + content + ", likeCount="
				+ likeCount + ", status=" + status + ", authority=" + authority + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

	public PictureVO getPictureVO() {
		return pictureVO;
	}

	public void setPictureVO(PictureVO pictureVO) {
		this.pictureVO = pictureVO;
	}

	public PictureVO getPictureVO2() {
		return pictureVO2;
	}

	public void setPictureVO2(PictureVO pictureVO2) {
		this.pictureVO2 = pictureVO2;
	}
	
	
}


