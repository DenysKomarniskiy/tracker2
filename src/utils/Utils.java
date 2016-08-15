package utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

public class Utils {

	public static Map<String, String> getCookiesMap(Cookie[] cookies) {
		Map<String, String> cook = new HashMap<String, String>();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cook.put(cookie.getName(), cookie.getValue());
			}
		}

		return cook;
	}

}
