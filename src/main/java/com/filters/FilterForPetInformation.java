package com.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pet.model.PetVO;
import com.pet.service.PetService;

// @WebFilter(filterName = "petFilter", urlPatterns = { "/pet", "/activity", "/weight", "/remind" })
																									// 建立在web.xml排在登入後
public class FilterForPetInformation extends HttpFilter {
	private static final long serialVersionUID = 1L;

	public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		PetService petSvc = new PetService();
		Integer loginId = session.getAttribute("membersVO")==null ? -999:((com.members.model.MembersVO)session.getAttribute("membersVO")).getMemberId();
		Integer memberId = loginId;
		if(req.getParameter("memberId")!=null) {
			memberId = Integer.parseInt(req.getParameter("memberId"));
		}
		// 確認session是否為訪客
		if(loginId<0) {
			session.setAttribute("location", req.getRequestURL());
			res.sendRedirect(req.getContextPath() + "/front/login.jsp");
			return;// 訪客導至登入頁
		}
		Integer petId = petSvc.getByMemberId(memberId).get(0).getPetId();
		PetVO petVO = petSvc.getByPetId(petId);
		System.out.println("-----------------------------------------"+petVO.getPetName());
		// 確認前往頁面是否為本人主頁
		if(loginId != memberId) {
			// 確認前往主頁存在他人寵物
			if(petVO.getPetName() == null) {
				res.sendRedirect(req.getContextPath() + "/index.html");
				// 他人無寵物，去首頁
			}else {
				chain.doFilter(req, res);
				// 他人有寵物，隱藏部分
			}
		} else {
			String action = req.getParameter("action");
			boolean goToInsert = (req.getContextPath() + "/pet").equals(req.getRequestURI());
			if ("insert".equals(action) && goToInsert && petVO.getPetName() == null) {
				chain.doFilter(req, res);
				// 新增寵物送出時
			}
			else if(petVO.getPetName() == null) {
				session.setAttribute("location", req.getRequestURL());
				res.sendRedirect(req.getContextPath() + "/front/pet/new.jsp");
				// 本人沒有寵物導至新增頁面
			}
			else {
				chain.doFilter(req, res);
				// 確認前往主頁存在本人寵物
			}
			
			
		}
		

	}

}
