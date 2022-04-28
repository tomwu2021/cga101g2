package com.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MembersVO;
import com.mysql.cj.Session;

public class FilterForMemberLogin extends HttpFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 從 Session 來判斷 會員 是否登入
		HttpSession session = req.getSession();
		MembersVO membersVO = (MembersVO)session.getAttribute("membersVO");
		
		if(membersVO == null) {
			session.setAttribute("location", req.getRequestURL()); // 取得目前拜訪的網頁動態路徑，方便登入後重新回到此網頁
			res.sendRedirect(req.getContextPath()+"/front/login.jsp"); // 重新導向到 login.jsp 登入畫面
			return;
		}else {
			chain.doFilter(req, res);
		}

	}

	public void destroy() {
		config = null;
	}

}
