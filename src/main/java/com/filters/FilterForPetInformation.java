package com.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pet.model.PetVO;
import com.pet.service.PetService;

@WebFilter(filterName = "petFilter", urlPatterns = { "/pet", "/activity", "/weight", "/remind" }) // TODO
																									// 建立在web.xml排在登入後
public class FilterForPetInformation extends HttpFilter {
	private static final long serialVersionUID = 1L;

	public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		PetService petSvc = new PetService();
		Integer memberId = -999;
		if(req.getParameter("memberId") == null) {
			memberId = ((com.members.model.MembersVO)(req.getSession().getAttribute("membersVO"))).getMemberId();
		} else {
		memberId = Integer.parseInt(req.getParameter("memberId"));
		}
		if(memberId==-999) {
			session.setAttribute("location", req.getRequestURL());
			res.sendRedirect(req.getContextPath() + "/front/login.jsp");
			return;
		}
		Integer petId = petSvc.getByMemberId(memberId).get(0).getPetId();
		PetVO petVO = petSvc.getByPetId(petId);
		String action = req.getParameter("action");
		boolean goToInsert = (req.getContextPath() + "/pet").equals(req.getRequestURI());
		if ("insert".equals(action) && goToInsert) {
			chain.doFilter(req, res);
		} else if (petVO.getPetName() == null) {
			session.setAttribute("location", req.getRequestURL());//TODO  非本人
			res.sendRedirect(req.getContextPath() + "/front/pet/new.jsp");
			return;
		} else {
			chain.doFilter(req, res);
		}

	}

}
