package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadFilesFromFolder {
	public static File folder; // = new File("E:/SilkTestSandbox/TQG_TotalQC");
	public static List<String> listOfFiles = new ArrayList<String>();
	static String temp = "";

	public static List<String> listFilesForFolder(final File folder, String filter) {

		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry, filter);
			} else {
				if (fileEntry.isFile()) {
					temp = fileEntry.getName();
					if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals(filter))
						// System.out.println("File= " +
						// folder.getAbsolutePath()+ "\\" +
						// fileEntry.getName());
						listOfFiles.add(folder.getAbsolutePath() + "\\" + fileEntry.getName());
				}
			}
		}
		return listOfFiles;
	}
}