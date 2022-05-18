package com.report.model;

import com.common.exception.JDBCException;
import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.orders.model.OrdersVO;

import java.util.List;

public class ReportService {
	private ReportDAO_interface dao;
	
	public ReportService() {
		dao=new ReportJDBCDAO();
	}
	
	public ReportVO insert(Integer reporterId,Integer postId,String reportReason) {
		ReportVO reportVO=new ReportVO();
		reportVO.setReporterId(reporterId);
		reportVO.setPostId(postId);
		reportVO.setReportReason(reportReason);
		return dao.insert(reportVO);
	}
}
