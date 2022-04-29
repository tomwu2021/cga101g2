package com.sort_mix.controller;

import static com.util.GSONUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sort_mix.model.SortMixService;

@WebServlet("/shop/getAllSortMixBySort1VO")
public class getAllSortMixBySort1VO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SortMixService sortMixService = new SortMixService();
		writePojo2Json(response,sortMixService.getAll());
	}
}
