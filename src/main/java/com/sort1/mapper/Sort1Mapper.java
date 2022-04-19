package com.sort1.mapper;

import com.sort1.model.Sort1VO;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface Sort1Mapper {
	/**
	 * 定義Sort1VO
	 */

	@Insert(value= "INSERT INTO CGA_02.SORT1(sort1_name) values (#{sort1Name}) ")
	@Options(useGeneratedKeys = true, keyProperty = "sort1Id", keyColumn = "sort1_id")
	int insert(Sort1VO sort1VO);
	
	@Insert(value= "DELETE FROM CGA_02.SORT2 WHERE sort1_id = #{sort1Id} ")
	@Options(useGeneratedKeys = true, keyProperty = "sort1Id", keyColumn = "sort1_id")
	int deleteById(Integer id);

	@Insert(value= "UPDATE CGA_02.SORT1 SET sort1_name = #{sort1Name}"
				+ "	WHERE sort1_id = #{sort1Id}")
	@Options(keyProperty = "sort1Id", keyColumn = "sort2_id")
	int updateById(Sort1VO sort1VO);

	Sort1VO getOneById(Integer id);

	List<Sort1VO> selectAll();
}
