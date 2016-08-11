package utils;

import java.util.List;
import java.util.Map;

public class Distributor {

	public static String getUserIdWithMinTime(Map<String, List<Long>> timePerUser, List<String> excludeUsers) {
		String maxCountUser = null;
		String minDurationUser = null;
		Long maxCount = (long) 0;
		Long minDuration = Long.MAX_VALUE;

//		for (String key : timePerUser.keySet()) {
//			if (excludeUsers.contains(key)) {
//				continue;
//			}
//
//			Long value = timePerUser.get(key).get(0);
//			if (value >= maxCount) {
//				maxCount = value;
//				maxCountUser = key;
//			}
//		}

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

//		if (maxCountUser == minDurationUser) {
//			excludeUsers.add(minDurationUser);
//			if (excludeUsers.size() != timePerUser.keySet().size()) {
//				minDurationUser = getUserIdWithMinTime(timePerUser, excludeUsers);
//			}
//		}

		return minDurationUser;
	}

}
