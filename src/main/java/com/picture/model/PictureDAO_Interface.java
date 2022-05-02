package com.picture.model;

import java.sql.Connection;
import java.util.List;

import javax.naming.ldap.PagedResultsControl;

import com.common.model.JDBCDAO_Interface;
import com.common.model.MappingTableDto;
import com.common.model.PageQuery;
import com.common.model.PageResult;

public interface PictureDAO_Interface extends JDBCDAO_Interface<PictureVO> {

	public List<PictureVO> queryPicturesByMapping(MappingTableDto mtd);

	public Integer deleteById(Integer pictureId);

	public PageResult<PictureResult> getPageResult(PageQuery pageQuery);
}
