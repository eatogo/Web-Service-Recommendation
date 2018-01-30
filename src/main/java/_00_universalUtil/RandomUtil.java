package _00_universalUtil;

import java.util.Map;
import java.util.Set;

import _01_model.Food;

public class RandomUtil {
	/*
	 * Map物件menu需裝準備要亂數的Food物件，key是各Food物件的food_id
	 * 
	 * foodIdArray用來裝Map物件menu的key值
	 * 
	 * Map物件threeRandomRecommend用來裝要回傳的三個亂數推薦的Food物件
	 * key是各該Food物件的food_id
	 */
	private Map<Integer, Food> menu = null;
	private Integer[] foodIdArray = null;
	private Map<Integer, Food> threeRandomRecommend = null;
	
	public RandomUtil(Map<Integer, Food> menu) {
		super();
		this.menu = menu;
	}
	
	public Map<Integer, Food> getThreeRandomDishes() {
		randomizeMenu();
		threeRandomRecommend.put(foodIdArray[0], menu.get(foodIdArray[0]));
		threeRandomRecommend.put(foodIdArray[1], menu.get(foodIdArray[1]));
		threeRandomRecommend.put(foodIdArray[2], menu.get(foodIdArray[2]));
		return threeRandomRecommend;
	}
	
	private void randomizeMenu() {
		extractFoodIdsToArray();
		int picks = 3;
		for (int index = 0; index < picks; index++) {
		    int randomIndex = (int)(Math.random() * foodIdArray.length);
		    int temp = foodIdArray[randomIndex];
		    foodIdArray[randomIndex] = foodIdArray[index];
		    foodIdArray[index] = temp;
		}
	}
	
	private void extractFoodIdsToArray() {
		Set<Integer> keys = menu.keySet();
		foodIdArray = keys.toArray(new Integer[keys.size()]);
	}
}
