package com.report.model;

import com.common.model.JDBCDAO_Interface;

public interface ReportDAO_interface extends JDBCDAO_Interface<ReportVO>{

	boolean updateStatus(Integer reportId,Integer status);
	
}
