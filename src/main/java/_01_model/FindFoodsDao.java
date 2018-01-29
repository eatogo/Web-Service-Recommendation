package _01_model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import _00_universalUtil.DbConnector;

public class FindFoodsDao {
	private ResultSet resultOfSelectQuery = null;
	private Map<Integer, Food> menu = null;
	private Map<Integer, Store> storePortfolio = null;

	private String findFoodByTypeSql = "SELECT * FROM `FOODS` WHERE food_type = ?;";

	public Map<Integer, Food> findFoodByType(String food_type) {
		try {
			Connection conn = new DbConnector().connect();
			PreparedStatement ps = conn.prepareStatement(findFoodByTypeSql);
			ps.setString(1, food_type);
			resultOfSelectQuery = ps.executeQuery();
			while (resultOfSelectQuery.next()) {
				Integer food_id = resultOfSelectQuery.getInt(1);
				String food_name = resultOfSelectQuery.getString(2);
				Integer food_price = resultOfSelectQuery.getInt(3);
				String food_intro = resultOfSelectQuery.getString(4);
				String food_pic_hdpi = resultOfSelectQuery.getString(5);
				String food_pic_ldpi = resultOfSelectQuery.getString(6);
				String food_pic_mdpi = resultOfSelectQuery.getString(7);
				String food_pic = resultOfSelectQuery.getString(8);
				Integer food_limit = resultOfSelectQuery.getInt(9);
				Integer food_store = resultOfSelectQuery.getInt(11);
				String food_status = resultOfSelectQuery.getString(12);
				Long food_review_count = resultOfSelectQuery.getLong(13);
				Food dish = new Food(food_id, food_name, food_price, food_intro, food_pic_hdpi, food_pic_ldpi,
						food_pic_mdpi, food_pic, food_limit, food_type, food_store, food_status, food_review_count);
				menu.put(food_id, dish);
			}
			return menu;
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return null;
		}
	}

	private String findStoreByStoreSql = "SELECT * FROM `STORES` WHERE (store_latitude BETWEEN ? AND ?) AND (store_longitude BETWEEN ? AND ?);";

	public Map<Integer, Store> findStoreByLonLat(Double north_latitude, Double south_latitude, Double east_longitude,
			Double west_longitude) {
		try {
			Connection conn = new DbConnector().connect();
			PreparedStatement ps = conn.prepareStatement(findStoreByStoreSql);
			ps.setDouble(1, north_latitude);
			ps.setDouble(2, south_latitude);
			ps.setDouble(3, east_longitude);
			ps.setDouble(4, west_longitude);
			resultOfSelectQuery = ps.executeQuery();
			while (resultOfSelectQuery.next()) {
				Integer store_id = resultOfSelectQuery.getInt(1);
				String store_name = resultOfSelectQuery.getString(2);
				String store_address = resultOfSelectQuery.getString(3);
				String store_phone = resultOfSelectQuery.getString(4);
				String store_email = resultOfSelectQuery.getString(5);
				String store_logo = resultOfSelectQuery.getString(6);
				String store_open_hour = resultOfSelectQuery.getString(7);
				String store_intro = resultOfSelectQuery.getString(8);
				Double store_latitude = resultOfSelectQuery.getDouble(9);
				Double store_longitude = resultOfSelectQuery.getDouble(10);
				String store_city = resultOfSelectQuery.getString(11);
				String store_region = resultOfSelectQuery.getString(12);
				String store_operate_type = resultOfSelectQuery.getString(13);
				String store_status = resultOfSelectQuery.getString(14);
				Store store = new Store(store_id, store_name, store_address, store_phone, store_email, store_logo,
						store_open_hour, store_intro, store_latitude, store_longitude, store_city, store_region,
						store_operate_type, store_status);
				storePortfolio.put(store_id, store);
			}
			return storePortfolio;
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return null;
		}
	}

	private String findFoodByStoreSql = "SELECT * FROM `FOODS` WHERE food_store = ?;";

	public Map<Integer, Food> findFoodByStore(Integer store_id) {
		try {
			Connection conn = new DbConnector().connect();
			PreparedStatement ps = conn.prepareStatement(findFoodByStoreSql);
			ps.setInt(1, store_id);
			resultOfSelectQuery = ps.executeQuery();
			while (resultOfSelectQuery.next()) {
				Integer food_id = resultOfSelectQuery.getInt(1);
				String food_name = resultOfSelectQuery.getString(2);
				Integer food_price = resultOfSelectQuery.getInt(3);
				String food_intro = resultOfSelectQuery.getString(4);
				String food_pic_hdpi = resultOfSelectQuery.getString(5);
				String food_pic_ldpi = resultOfSelectQuery.getString(6);
				String food_pic_mdpi = resultOfSelectQuery.getString(7);
				String food_pic = resultOfSelectQuery.getString(8);
				Integer food_limit = resultOfSelectQuery.getInt(9);
				String food_type = resultOfSelectQuery.getString(10);
				Integer food_store = store_id;
				String food_status = resultOfSelectQuery.getString(12);
				Long food_review_count = resultOfSelectQuery.getLong(13);
				Food dish = new Food(food_id, food_name, food_price, food_intro, food_pic_hdpi, food_pic_ldpi,
						food_pic_mdpi, food_pic, food_limit, food_type, food_store, food_status, food_review_count);
				menu.put(food_id, dish);
			}
			return menu;
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return null;
		}
	}
}
