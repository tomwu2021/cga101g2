package com.article.model;

import java.sql.Timestamp;
import java.util.List;

public class ArticleVO {

	private Integer articleId;
	private Integer type;
	private String title;
	private String content;
	private Timestamp createTime;
	private Integer empNo;
	
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	public com.emp.model.EmpVO getEmpVO(){
		com.emp.model.EmpService empSvc =new com.emp.model.EmpService();
		com.emp.model.EmpVO empVO = empSvc.getOneById(empNo);
		return empVO;
	}
	public com.picture.model.PictureVO getPicVO(){
		com.article.service.ArticleService artiSvc =new com.article.service.ArticleService();
		List<com.picture.model.PictureVO> picsVO = artiSvc.getOneArticlePic(articleId);
		com.picture.model.PictureVO picVO = picsVO.get(0);
		return picVO;
	}
	@Override
	public String toString() {
		return "ArticleVO[ "+ articleId + ", " + type + ", " + title + ", " + content + ", " + createTime + ", "  + empNo + " ]";
	}
	
}
