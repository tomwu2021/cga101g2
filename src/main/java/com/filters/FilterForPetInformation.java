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

		HttpSession session = req.getSession();
		PetService petSvc = new PetService();
		Integer petId = (Integer) session.getAttribute("petId");
		PetVO petVO = petSvc.getByPetId(petId);
		String action = req.getParameter("action");
		boolean goToInsert = (req.getContextPath() + "/pet").equals(req.getRequestURI());
		if ("insert".equals(action) && goToInsert) {
			chain.doFilter(req, res);
		} else if (petVO.getPetName() == null) {
			session.setAttribute("location", req.getRequestURL());
			res.sendRedirect(req.getContextPath() + "/front/pet/new.jsp");
			return;
		} else {
			chain.doFilter(req, res);
		}

	}

}
