package com.product.model;

import connection.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ProductRedis {

	private static JedisPool pool = null;

	// 輸入productId 獲得 totalView ,如果資料庫沒有這筆資料,就去創相對應的key
	public int getProductIdTotalView(Integer productId) {
//		System.out.println("getProductIdTotalView執行成功");
		
		Integer totalView =null;
		pool = JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		
		try  {
			///特別注意 切換DB空間
			jedis.select(2);
			jedis.ping();
			String key = "productId:"+productId+":totalView"; 
//			System.out.println("key值 :"+key);
//			System.out.println("redis回傳結果: "+jedis.get(key)); 
			if(jedis.get(key)==null) {
				jedis.set(key, "1");
			}
			totalView = Integer.parseInt(jedis.get(key));
//			System.out.println(totalView);
			
		} catch (Exception e) {
	        e.getMessage();
	    }finally{
			if (jedis != null)
				jedis.close();
		}
		return totalView;
	}
	
	
	// 輸入productId 新增 totalView ,如果資料庫沒有這筆資料,就去創相對應的key
	public int addProductIdTotalView(Integer productId) {
//		System.out.println("addProductIdTotalView執行成功");
		
		Integer newTotalView =null;
		pool = JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		
		try  {
			///特別注意 切換DB空間
			jedis.select(2);
			jedis.ping();
			String key = "productId:"+productId+":totalView"; 
//			System.out.println("key值 :"+key);
//			System.out.println("redis回傳結果: "+jedis.get(key)); 
			if(jedis.get(key)==null) {
				jedis.set(key, "1");
			}
			//incrBy:按指定數增長
			jedis.incrBy(key,1);
			newTotalView = Integer.parseInt(jedis.get(key));
		} catch (Exception e) {
	        e.getMessage();
	    } finally{
			if (jedis != null)
				jedis.close();
		}
		return newTotalView;
	}
	
	public static void main(String[] args) {
		ProductRedis productRedis = new ProductRedis();
//		productRedis.addProductIdTotalView(1);
		productRedis.getProductIdTotalView(1);
	}
}

