package com.report.model;

import java.util.List;

public interface ReportDAO_interface {
	public List<ReportVO> getAll();
	public void insert(ReportVO reportVO);
	public void update(ReportVO reportVO);
	public void delete(Integer report_id);
	public ReportVO findByPrimaryKey(Integer report_id);	
}
