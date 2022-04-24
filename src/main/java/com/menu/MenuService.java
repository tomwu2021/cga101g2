package com.menu;

import com.menu.mapper.MenuMapper;
import com.menu.model.MenuDAO;
import com.menu.model.MenuVO;
import connection.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuService {
    MenuDAO menuDAO = new MenuDAO();

    public List<Map<String, String>> getBreadcrumbList(String contextPath, String page) {
        List<Map<String, String>> breadcrumbList = new ArrayList<>();

        List<MenuVO> menus = menuDAO.getSuperMenusByPage(page);
        Map<String, String> root = new HashMap<>();
        root.put("name", "Home");
        root.put("path", contextPath + "/index.html");
        breadcrumbList.add(root);

        for (MenuVO menu : menus) {
            Map<String, String> mMap = new HashMap<>();
            mMap.put("name", menu.getName());
            mMap.put("path", contextPath + menu.getPath());
            breadcrumbList.add(mMap);
        }
        return breadcrumbList;
    }
}
