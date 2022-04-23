package com.sortMix.controller;

import static com.util.GSONUtil.writePojo2Json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sort1.model.Sort1VO;
import com.sort1.service.Sort1Service;
import com.sort_mix.model.SortMixMapVO;
import com.sort_mix.service.SortMixService;

@WebServlet("/front/shop/getAllSortMix")
public class getAllSortMix extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		SortMixService sortMixService = new SortMixService();
//		ArrayList listall = new ArrayList(); 
//		writePojo2Json(response, sortMixService.getAll());
//		Sort1Service sort1Service = new Sort1Service();
//		writePojo2Json(response, sort1Service.findAll());
		Sort1Service sort1Service = new Sort1Service();
		List<Sort1VO> list1 = sort1Service.findAll();
		
		SortMixService sortMixService = new SortMixService();
		List<SortMixMapVO> list2 = sortMixService.getAll();
		
		ArrayList<List<?>> listAll = new ArrayList<List<?>>(); 
		listAll.add(list1);
		listAll.add(list2);
		writePojo2Json(response, listAll);
//		
////		打包成更好的json格式
//		JsonObject object = new JsonObject(); // 建立一個json物件
//		JsonObject sort1o = new JsonObject(); // 建立json陣列
//		JsonArray sort1a = new JsonArray();
//		JsonObject sort2o = new JsonObject(); // 建立json陣列
//		JsonArray sort2a = new JsonArray();
//		
//		System.out.println(list2.size());
//		for (int i = 0; i < list1.size(); i++) {
//			object = new JsonObject();
//			sort1o = new JsonObject();
//			sort1o.addProperty("sort1id", list1.get(i).getSort1Id()); //物件
//			sort1o.addProperty("sort1Name", list1.get(i).getSort1Name());  //物件
//			sort1a.add(sort1o);
//			int Sort1Id = list1.get(i).getSort1Id();
//			
//			for (int a = 0; a < list2.size(); a++) {
//				int sort1id = list2.get(i).getId1();
//				if (Sort1Id == sort1id) {
//					sort2o = new JsonObject();
//					sort2o.addProperty("sort1id", list2.get(a).getId1());
//					sort2o.addProperty("sort1Name", list2.get(a).getColumn1());
//					sort2o.addProperty("sort2id", list2.get(a).getId2());
//					sort2o.addProperty("sort2Name", list2.get(a).getColumn2());
//					sort2a.add(sort2o);
//				}
//				object = new JsonObject();
//				object.add("sort1Set",sort1a);
//				object.add("sort1ToSort2Set",sort2a);
//			}
//			
//		}
//		System.out.println(object);
//		writePojo2Json(response, object);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
