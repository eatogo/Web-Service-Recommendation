package _01_model;

import java.util.Map;
import java.util.Set;

public class RandomRecommend {
	private Map<Integer, Food> menu = null;
	private Integer[] foodIdArray = null;
	private Map<Integer, Food> threeRandomRecommend = null;
	
	public RandomRecommend(Map<Integer, Food> menu) {
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
		for (int i = 0; i < picks; i++) {
		    int randomIndex = (int)(Math.random() * foodIdArray.length);
		    int temp = foodIdArray[randomIndex];
		    foodIdArray[randomIndex] = foodIdArray[i];
		    foodIdArray[i] = temp;
		}
	}
	
	private void extractFoodIdsToArray() {
		Set<Integer> keys = menu.keySet();
		foodIdArray = keys.toArray(new Integer[keys.size()]);
	}
}
