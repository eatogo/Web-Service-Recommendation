package _02_controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _00_universalUtil.JsonUtil;
import _00_universalUtil.model.Food;
import _00_universalUtil.model.FoodWithLatLng;
import _01_model.FindFoodDao;


/**
 * 這支Servlet負責接收依照餐點種類查詢餐點的請求
 * 
 * 請求必須以JSON格式寫進來；回應也一律以JSON格式回傳
 * 
 * 搜尋功能可以依定位經緯度縮小搜尋範圍
 * 若要使用定位功能，請附上經緯度參數：{"food_type":"xxxxx","latitude"="xx.xxxxx","longitude"="xxx.xxxxx"}
 */
@WebServlet("/RequestFoodByType.do")
public class RequestFoodByTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	Map<String, String> errorMessage = new HashMap<String, String>();
	List<Food> foods = new ArrayList<Food>();
	JsonUtil jsonUtil = new JsonUtil();
	StringBuffer jsonInString, jsonOutString;
	FoodWithLatLng foodWithLatLng = new FoodWithLatLng();
	String food_type;
	Double latitude, longitude;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		FindFoodDao dao = (FindFoodDao) ctx.getBean("findFoodJDBCDS");
		request.setCharacterEncoding("UTF8");

		// 開始讀取clien端傳來json字串動作
		BufferedReader br = request.getReader();
		jsonInString = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonInString.append(line);
		}
		br.close();

		foodWithLatLng = jsonUtil.convertToFoodWithLatLngFrom(jsonInString.toString());
		food_type = foodWithLatLng.getFood_type();
		try {
			latitude = foodWithLatLng.getStore_latitude();
			longitude = foodWithLatLng.getStore_longitude();
			// 依定位縮小搜尋範圍
			foods = dao.findWithinDistanceFoodBy(food_type, latitude, longitude);
			// System.out.println(foods.get(0).toString());
			sendJsonWithWriter(response);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			foods = dao.findFoodBy(food_type);
			sendJsonWithWriter(response);
		}
	}
	
	private void sendJsonWithWriter(HttpServletResponse response) throws IOException {
		response.setContentType(CONTENT_TYPE);
		if (!foods.isEmpty()) {
			jsonOutString = new StringBuffer();
			jsonOutString.append(jsonUtil.listTojson(foods));
			PrintWriter out = response.getWriter();
			out.println(jsonOutString);
			out.close();
			return;
		} else {
			PrintWriter out = response.getWriter();
			out.println("No-data nearby");
			out.close();
			return;
		}
	}

}
