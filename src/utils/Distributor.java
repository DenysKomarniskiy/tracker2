package utils;

import java.util.List;
import java.util.Map;

public class Distributor {

	public static String getUserIdWithMinTime(Map<String, List<Long>> timePerUser, List<String> excludeUsers) {

		String minDurationUser = null;
		String maxCountUser = null;

		maxCountUser = findUserWithMaxCount(timePerUser, excludeUsers);

		minDurationUser = findUserWithMinDuration(timePerUser, excludeUsers);

		if (maxCountUser == minDurationUser) {
			excludeUsers.add(minDurationUser);
			if (excludeUsers.size() < 2) {
				minDurationUser = getUserIdWithMinTime(timePerUser, excludeUsers);
			}
		}

		return minDurationUser;
	}

	private static String findUserWithMaxCount(Map<String, List<Long>> timePerUser, List<String> excludeUsers) {
		Long maxCount = (long) 0;
		String maxCountUser = null;

		for (String key : timePerUser.keySet()) {
			if (excludeUsers.contains(key)) {
				continue;
			}
			Long value = timePerUser.get(key).get(0);
			if (value >= maxCount) {
				maxCount = value;
				maxCountUser = key;
			}
		}

		return maxCountUser;
	}

	private static String findUserWithMinDuration(Map<String, List<Long>> timePerUser, List<String> excludeUsers) {
		Long minDuration = Long.MAX_VALUE;
		String minDurationUser = null;

		for (String key : timePerUser.keySet()) {
			if (excludeUsers.contains(key)) {
				continue;
			}
			Long value = timePerUser.get(key).get(1);
			if (value <= minDuration) {
				minDuration = value;
				minDurationUser = key;
			}
		}

		return minDurationUser;
	}

}
