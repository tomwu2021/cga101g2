package com.product.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *     所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */
public class jdbcUtil_CompositeQuery_Product {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;
//**挑選個別欄位加各自的表格名稱 ,比如A的欄位表加A的表格 B的欄位加B的表格**//
		if ("product_id".equals(columnName) || "price".equals(columnName) || "amount".equals(columnName)
				|| "sort2_id".equals(columnName) 
				|| "group_price1".equals(columnName)
				|| "group_amount1".equals(columnName) || "group_amount2".equals(columnName)
				|| "group_amount3".equals(columnName) || "status".equals(columnName) || "top_status".equals(columnName)) // 用於其他
			aCondition = "product." + columnName + "=" + value;
		else if ("product_name".equals(columnName) || "description".equals(columnName)) // 用於varchar
			aCondition = "product." + columnName + " like '%" + value + "%'";
		
//		else if ("update_time".equals(columnName)) // 用於date
//			aCondition = "product." + columnName + "=" + "'" + value + "'"; // for 其它DB 的 date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		System.err.println("==============================================");
		System.out.println("name: " + columnName + "value: " + value);
		if (aCondition == null) {
			return " ";
		} else {
			return aCondition + " ";
		}
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

//				if (count == 1)
//					whereCondition.append(" where " + aCondition);
//				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		System.out.println(whereCondition.toString());
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

//測試萬用查詢		
		// 配合 req.getParameterMap()方法 回傳
//		 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("product_id", new String[] { "1" });
//		map.put("product_name", new String[] { "貓" });
//		map.put("price", new String[] { "700" });
//		map.put("amount", new String[] { "13" });
//		map.put("sort2_id", new String[] { "11" });
//		map.put("sort1_id", new String[] { "1" });
//		map.put("update_time", new String[] { "1981-11-17" });
//		map.put("group_price1", new String[] { "10" });
//		map.put("group_amount1", new String[] { "10" });
//		map.put("group_amount2", new String[] { "10" });
//		map.put("group_amount3", new String[] { "10" });
//		map.put("description", new String[] { "好吃" });
		map.put("status", new String[] { "0" });
//		測試錯誤的
//		map.put("top_status", new String[] { "0" });
//		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from product "
				+ "JOIN p_sort1 "
				+" ON product.product_id = p_sort1.product_id "	
				// 後臺要多拉一對一的表
				+" JOIN sort2 "	
				+" ON product.sort2_id = sort2.sort2_id "	
				        + jdbcUtil_CompositeQuery_Product.get_WhereCondition(map)
				        + "group by product.product_id "
				        + "order by product.product_id DESC";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
