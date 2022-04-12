package com.report.model;

import java.util.List;

public class ReportTestDAO {
	public static void main(String[] args) {
		
		ReportJDBCDAO dao = new ReportJDBCDAO();
		
		//查詢全部
		List<ReportVO> list = dao.getAll();
		for(ReportVO vo: list) {
			System.out.print(vo.getReport_id()+ ",");
			System.out.print(vo.getReporter_id()+ ",");
			System.out.print(vo.getPost_id()+ ",");
			System.out.print(vo.getReport_reason()+ ",");
			System.out.print(vo.getReport_time()+ ",");
			System.out.print(vo.getStatus());
			System.out.println();	
		}
		
		//新增
		
		ReportVO reportvo1 = new ReportVO();
		
//		reportvo1.setReporter_id(1);
//		reportvo1.setPost_id(3);
//		reportvo1.setReport_reason("bloody violence");
//		reportvo1.setStatus(0);
		
//		reportvo1.setReporter_id(4);
//		reportvo1.setPost_id(2);
//		reportvo1.setReport_reason("reporting spam text messages");
//		reportvo1.setStatus(1);
	
		dao.insert(reportvo1);	
	}
}
