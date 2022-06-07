package com.report.model;

import java.util.List;

public class ReportService {
	private ReportDAO_interface dao;
	
	public ReportService() {
		dao=new ReportJDBCDAO();
	}
	
	public ReportVO insert(Integer reporterId,Integer postId,String reportReason,String url) {
		ReportVO reportVO=new ReportVO();
		reportVO.setReporterId(reporterId);
		reportVO.setPostId(postId);
		reportVO.setReportReason(reportReason);
		reportVO.setUrl(url);;
		return dao.insert(reportVO);
	}
	
	public List<ReportVO> getAll() {
		return dao.getAll();
	}
	
	public boolean updateStatus(Integer reportId,Integer status) {
		return dao.updateStatus(reportId, status);
	}
}
