package _00_universalUtil;

import _01_model.Food;
import _01_model.Store;

/*
 * 此類別負責將物件轉換為JSON
 * 或將JSON轉換為物件
 */
public class JsonUtil {
	private String json;
	
	public void jsonToObject() {
		
	}
	
	public void ObjectToJson(Object object) {
		if (object instanceof Food) {
			foodToJson((Food) object);
		} else if (object instanceof Store) {
			storeToJson((Store) object);
		}
	}
	
	private String foodToJson(Food food) {
		
		return json;
	}
	
	private String storeToJson(Store store) {
		
		return json;
	}
}
