package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.entities.StorageTC;

public class Utils {

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

	public static Map<String, String> getCookiesMap(Cookie[] cookies) {
		Map<String, String> cook = new HashMap<String, String>();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cook.put(cookie.getName(), cookie.getValue());
			}
		}

		return cook;
	}

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

	public void setRunOpts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> listTc = new ArrayList<String>();
		List<String> listOfFiles = new ArrayList<String>();
		String sTestCaseNumber, sTestCase, sIssueNumber = "", sTestCaseNameInSilkTest, sPattern, sFullPath = "", sShortPath;
		List<String> lsFullPath = new ArrayList<String>();

		boolean bFake = false, bCritical = false;

		SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();
		Transaction tx;

		tx = hibernateSession.beginTransaction();

		List<StorageTC> tcList = (List<StorageTC>) hibernateSession.createQuery("SELECT DISTINCT stc FROM StorageTC stc LEFT JOIN FETCH stc.testSet").getResultList();
		tx.commit();

		for (StorageTC tc : tcList) {
			if (tc.getFeatures() != null) {
				bCritical = tc.getFeatures().toLowerCase().contains("critical");
				bFake = tc.getFeatures().toLowerCase().contains("fake tc");
			}

			if (!tc.getAuto_ide().toLowerCase().equals("tc") && !bCritical && !bFake) {
				listTc.add(tc.getTc_id());
			}

		}
		folder = new File("E:/SilkTestSandbox/TQG_TotalQC");
		listOfFiles = listFilesForFolder(folder, "t");

		for (String tc : listTc) {
			if (tc.contains("_")) { // For TC with such tc_id in DB
									// "896-IS_2184-IS"
				sTestCaseNumber = tc.split("_")[1];
				sTestCase = sTestCaseNumber.split("-")[0] + sTestCaseNumber.split("-")[1];
				sIssueNumber = tc.split("_")[0].split("-")[0] + tc.split("_")[0].split("-")[1];
			} else { // For TC with such tc_id in DB "999-IS"
				sTestCase = tc.split("-")[0] + tc.split("-")[1];
			}

			sTestCaseNameInSilkTest = "";
			hibernateSession = sessionFactory.getCurrentSession();
			tx = hibernateSession.beginTransaction();

			Query query = hibernateSession.createQuery("from StorageTC where tc_id= :tc_id").setParameter("tc_id", tc);
			StorageTC storageTC = (StorageTC) query.getSingleResult();
			storageTC = (StorageTC) hibernateSession.load(StorageTC.class, new Integer(storageTC.getId()));
			sPattern = "main_" + sTestCase + ".t"; // sPattern = "main_912SC.t"
			lsFullPath = getMatchingStrings(listOfFiles, sPattern);

			if (lsFullPath.size() > 0) {
				sFullPath = lsFullPath.get(0);
				sTestCaseNameInSilkTest = "main()";

			} else {
				sPattern = "\\\\" + sTestCase + ".t"; // sPattern = "999IS.t"
				lsFullPath = getMatchingStrings(listOfFiles, sPattern);

				if (lsFullPath.size() > 0) {
					sFullPath = lsFullPath.get(0);
					sTestCaseNameInSilkTest = "TQC_" + sTestCase;

				} else {
					sPattern = sIssueNumber + ".t"; // sPattern = "206890SC.t"
					lsFullPath = getMatchingStrings(listOfFiles, sPattern);

					if (lsFullPath.size() > 0) {
						sFullPath = lsFullPath.get(0);
						sTestCaseNameInSilkTest = "TQC_" + sTestCase;
					} else {

						// With duration 0 and Rules are not participated in
						// logic

					}
				}
			}

			storageTC.setRun_param(sTestCaseNameInSilkTest);
			storageTC.setRun_path(sFullPath);
			;

			hibernateSession.update(storageTC);
			tx.commit();

		}

	}

}
