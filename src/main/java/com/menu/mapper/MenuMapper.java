package com.menu.mapper;

import com.menu.model.MenuVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper {
    @Select(value = " SELECT * " +
            "   FROM menu m " +
            "  WHERE mid = (SELECT pid FROM menu m2 WHERE page = #{page})   " +
            "     OR mid = (SELECT pid FROM menu WHERE mid = (SELECT pid FROM menu WHERE page=#{page}) ) " +
            " OR page=#{page} ")
    List<MenuVO> findSuperMenusByPage(String page);
}
