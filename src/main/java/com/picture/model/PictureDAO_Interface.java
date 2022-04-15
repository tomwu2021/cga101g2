package com.picture.model;

import java.sql.Connection;
import java.util.List;

import com.common.model.JDBCDAO_Interface;
import com.common.model.MappingTableDto;

public interface PictureDAO_Interface extends JDBCDAO_Interface<PictureVO> {

	public List<PictureVO> queryPicturesByMapping(MappingTableDto mtd) ;
	public Integer deleteById(Integer pictureId);
	
}
