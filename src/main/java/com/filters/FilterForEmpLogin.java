package com.filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.emp.model.EmpVO;

public class FilterForEmpLogin implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		EmpVO empVO = (EmpVO)session.getAttribute("empVO");
		System.out.println(empVO+" 1");
		if(empVO == null) {
			System.out.println(empVO+" 2");
			session.setAttribute("locationEmp", req.getRequestURL()); 
			res.sendRedirect(req.getContextPath()+"/back/empLogin.jsp");
			return;
		}else {
			chain.doFilter(req, res);
		}

	}

	public void destroy() {
		config = null;
	}

}
