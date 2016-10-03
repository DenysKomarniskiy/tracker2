package utils;

import java.util.LinkedList;
import java.util.List;

public class CustomTesting {

	public String name;
	public List<CustomTestingItem> items = new LinkedList<>();
	public List<String> users = new LinkedList<>();

	public List<Integer> getIds() {

		List<Integer> ids = new LinkedList<Integer>();

		for (CustomTestingItem item : items) {
			ids.add(item.id);
		}

		return ids;
	}

	public String getUserByTcId(Integer id) {
		String user = null;

		for (CustomTestingItem item : items) {
			if (item.id == id) {
				user = item.runner;
			}
		}

		return user;
	}

}
