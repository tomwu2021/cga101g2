package com.report.model;

import java.util.List;

public class TestReport {
	public static void main(String[] args) {
		
		ReportJDBCDAO dao = new ReportJDBCDAO();
		
		//查詢全部
		List<ReportVO> list = dao.getAll();
		for(ReportVO vo: list) {
			System.out.print(vo.getReportId()+ ",");
			System.out.print(vo.getReporterId()+ ",");
			System.out.print(vo.getPostId()+ ",");
			System.out.print(vo.getReportReason()+ ",");
			System.out.print(vo.getReportTime()+ ",");
			System.out.print(vo.getStatus());
			System.out.println();	
		}
		
		//新增
//		ReportVO reportvo1 = new ReportVO();
//		
//		reportvo1.setReporterId(3);
//		reportvo1.setPostId(4);
//		reportvo1.setReportReason("垃圾訊息");
//		reportvo1.setStatus(0);
//	
//		dao.insert(reportvo1);	
	}
}
