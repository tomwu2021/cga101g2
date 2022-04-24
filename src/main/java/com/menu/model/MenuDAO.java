package com.menu.model;

import com.menu.mapper.MenuMapper;
import connection.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MenuDAO {
    public List<MenuVO> getSuperMenusByPage(String page) {
        SqlSession session = MyBatisUtil.getSessionTest();
        MenuMapper mm = session.getMapper(MenuMapper.class);
        return mm.findSuperMenusByPage(page);
    }
}
