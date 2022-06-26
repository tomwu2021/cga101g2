package core.util;

import static core.util.Constants.GSON;
import static core.util.Constants.JSON_MIME_TYPE;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static <P> P json2Pojo(String jsonData, Class<P> beanType) {
		try {
			P p = MAPPER.readValue(jsonData, beanType);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
