package com.sort_mix.model;

import java.util.List;

public class TESTSortMixDAO {
	public static void main(String[] args) {
		SortMixJDBCDAO dao = new SortMixJDBCDAO();

		// 新增分類組合
//		SortMixVO sortMixVO1 = new SortMixVO();
//		sortMixVO1.setSort1Id(2);
//		sortMixVO1.setSort2Id(14);
//		dao.insert(sortMixVO1);

		// 刪除分類組合
//		SortMixVO sortMixVO2 = new SortMixVO();
//		sortMixVO2.setSort1Id(2);
//		sortMixVO2.setSort2Id(8);
//		dao.delete(sortMixVO2);

		// 查詢主分類與子分類所有組合
		List<SortMixMapVO> list = dao.findAll();
		for (SortMixMapVO smvs : list) {
			System.out.print(smvs.getSort1Id() + ",");
			System.out.print(smvs.getSort1Name()+ ",");
			System.out.print(smvs.getSort2Id() + ",");
			System.out.print(smvs.getSort2Name()+ ",");
			System.out.println();
		}

//		打包成更好的json格式
//		JsonObject object = new JsonObject(); // 建立一個json物件
//		JsonObject sort1o = new JsonObject(); // 建立json陣列
//		JsonArray sort1a = new JsonArray();
//		JsonObject sort2o = new JsonObject(); // 建立json陣列
//		JsonArray sort2a = new JsonArray();
//
//		Sort1MybatisDAO sort1dao = new Sort1MybatisDAO();
//		List<Sort1VO> list1 = sort1dao.selectAll();
//		List<MappingTableDto> list2 = dao.getAll();
//		System.out.println(list2.size());
//		for (int i = 0; i < list1.size(); i++) {
//			object = new JsonObject();
//			sort1o = new JsonObject();
//			sort1o.addProperty("sort1id", list1.get(i).getSort1Id()); //物件
//			sort1o.addProperty("sort1Name", list1.get(i).getSort1Name());  //物件
//			sort1a.add(sort1o);
//			object.add("sort1Set", sort2a);
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
//				object.add("sort1ToSort2Set", sort2a);
//			}
//			
//		}
//		System.out.println(object);
		
	
	}

}
