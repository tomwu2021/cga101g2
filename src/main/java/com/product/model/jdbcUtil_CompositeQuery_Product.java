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

		if ("product_id".equals(columnName) || "price".equals(columnName) || "amount".equals(columnName) || "sort2_id".equals(columnName)
	   || "group_price1".equals(columnName) || "group_amount1".equals(columnName) || "group_amount2".equals(columnName) || "group_amount3".equals(columnName)
	   || "status".equals(columnName)      || "topStatus".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("product_name".equals(columnName) || "description".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("update_time".equals(columnName))                          // 用於date
			aCondition = columnName + "=" + "'"+ value +"'";                          //for 其它DB  的 date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		
		return aCondition + " ";
	}
	

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		System.out.println(whereCondition.toString());
		return whereCondition.toString();
	}
	
	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("product_id", new String[] { "1" });
//		map.put("product_name", new String[] { "貓" });
//		map.put("price", new String[] { "700" });
//		map.put("amount", new String[] { "13" });
//		map.put("sort2Id", new String[] { "2" });
//		map.put("update_time", new String[] { "1981-11-17" });
//		map.put("group_price1", new String[] { "10" });
//		map.put("group_amount1", new String[] { "10" });
//		map.put("group_amount2", new String[] { "10" });
//		map.put("group_amount3", new String[] { "10" });
//		map.put("description", new String[] { "好吃" });
		map.put("status", new String[] { "2" });
//		map.put("topStatus", new String[] { "0" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from product "
				          + jdbcUtil_CompositeQuery_Product.get_WhereCondition(map)
				          + "order by product_id";
		System.out.println("●●finalSQL = " + finalSQL);

	}
	
}
