package com.util;

import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSONUtil {

	public static DataSource DATASOURCE;
//	GsonBuilder()高階資料流,可填加其餘設定
	private static final Gson GSON = new GsonBuilder().create();
	public static final String JSON_MIME_TYPE = "application/json;";

	/*請求:
	 * 前提,前端已把資料轉換為JSON格式,Controller控制器把HTTPreq拋接給工具類別轉換格式 
	 * 從HTTPreq > JAVA接輸入水管,讓JAVA使用GSON API解析JSON > POJO 
	 * T fromJson(String json, Class<T> classOfT)
	 * Deserialization：反序列化，JSON字串轉換成Java物件(反序列化:0419課程)
	 */
	public static <P> P json2Pojo(HttpServletRequest request, Class<P> classOfPojo) {
		try (BufferedReader br = request.getReader()) {
			return GSON.fromJson(br, classOfPojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*回應:
	 * 後端應把欲回應的Java物件轉成JSON格式,在Controller控制器把POJO拋接給工具類別轉換成Httres(JSON格式) 
	 * 從JAVA > HTTPres接輸入水管,讓JAVA使用GSON API轉換POJO > JSON  
	 * JsonElement toJsonTree(Object src)
	 * Serialization:序列化，使Java物件到Json字串的過程
	 */
	public static <P> void writePojo2Json(HttpServletResponse response, P pojo) {
		//設定回應↓設定回應的資料格式為JSON 講義P128
		response.setContentType(JSON_MIME_TYPE + "charset=UTF-8");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(GSON.toJson(pojo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
