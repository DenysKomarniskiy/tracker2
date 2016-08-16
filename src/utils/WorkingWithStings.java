package utils;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class WorkingWithStings {

	public static List<String> getMatchingStrings(List<String> list, String regex) {

		ArrayList<String> matches = new ArrayList<String>();

		Pattern p = Pattern.compile(regex);
		for (String s : list) {
			if (p.matcher(s).find()) {
				matches.add(s);
			}
		}

		return matches;
	}
}