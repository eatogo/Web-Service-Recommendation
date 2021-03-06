package _00_universalUtil;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import _00_universalUtil.model.Food;
import _00_universalUtil.model.FoodWithLatLng;
import _00_universalUtil.model.Store;

/*
 * 此類別負責將物件轉換為JSON
 * 或將JSON轉換為物件
 */
public class JsonUtil {
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	public FoodWithLatLng convertToFoodWithLatLngFrom(String jsonString) {
		return gson.fromJson(jsonString, FoodWithLatLng.class);
	}
	
	public Object jsonToObject(String jsonString, Object typeIdicator) {
		return gson.fromJson(jsonString, typeIdicator.getClass());
	}
	
	public List<?> jsonToListOfFood(String jsonString) {
		Type collectionType = new TypeToken<List<Food>>() {	}.getType();
		return gson.fromJson(jsonString, collectionType);
	}
	
	public List<?> jsonToListOfStore(String jsonString) {
		Type collectionType = new TypeToken<List<Store>>() {	}.getType();
		return gson.fromJson(jsonString, collectionType);
	}
	
	public String objectToJson(Object object) {
		return gson.toJson(object);
	}
	
	public String listTojson(List<?> list) {
		return gson.toJson(list);
	}
	
}
