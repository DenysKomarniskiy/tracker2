package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.google.gson.Gson;

import models.entities.StorageTC;
import models.entities.TestingSheet;
import utils.ReadFilesFromFolder;
import utils.WorkingWithStings;

import models.entities.StorageTC;

@WebServlet("/storage")
public class Storage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Storage() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Gson gson = new Gson();
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		List testings = hibernateSession.createQuery("from Testing").getResultList();
		List users = hibernateSession.createQuery("from User").getResultList();
		List<StorageTC> tcs = hibernateSession.createQuery("SELECT DISTINCT stc FROM StorageTC stc LEFT JOIN FETCH stc.testSet").getResultList();
		tx.commit();

		request.setAttribute("title", "Storage");
		request.setAttribute("users", users);
		request.setAttribute("jusers", gson.toJson(users));
		request.setAttribute("testings", testings);
		request.setAttribute("tcs", gson.toJson(tcs));

		request.setAttribute("template", "storage.jsp");
		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> listTc = new ArrayList<String>();
		List<String> listOfFiles = new ArrayList<String>();
		String sTestCaseNumber, sTestCase, sIssueNumber = "", sTestCaseNameInSilkTest, sPattern, sFullPath = "", sShortPath;
		List<String> lsFullPath = new ArrayList<String>();

		boolean bFake = false,bCritical = false;
		
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();
		Transaction tx;

		tx = hibernateSession.beginTransaction();

		List<StorageTC> tcList = (List<StorageTC>) hibernateSession.createQuery("SELECT DISTINCT stc FROM StorageTC stc LEFT JOIN FETCH stc.testSet").getResultList();
		tx.commit();
		
		for (StorageTC tc : tcList) {
			if (tc.getFeatures() != null) {
				bCritical = tc.getFeatures().toLowerCase().contains("critical");
				bFake     = tc.getFeatures().toLowerCase().contains("fake tc");
			}

			if (!tc.getAuto_ide().toLowerCase().equals("tc") && !bCritical && !bFake) {
				listTc.add(tc.getTc_id());
			}

		}
		ReadFilesFromFolder.folder = new File("E:/SilkTestSandbox/TQG_TotalQC");
		listOfFiles = ReadFilesFromFolder.listFilesForFolder(ReadFilesFromFolder.folder, "t");
		
		for (String tc : listTc) {
			if (tc.contains("_")) { // For TC with such tc_id in DB "896-IS_2184-IS"
				sTestCaseNumber = tc.split("_")[1];
				sTestCase = sTestCaseNumber.split("-")[0] + sTestCaseNumber.split("-")[1];
				sIssueNumber = tc.split("_")[0].split("-")[0] + tc.split("_")[0].split("-")[1];
			} else { // For TC with such tc_id in DB "999-IS"
				sTestCase = tc.split("-")[0] + tc.split("-")[1];
			}

			sTestCaseNameInSilkTest = "";
			hibernateSession = sessionFactory.getCurrentSession();
			tx = hibernateSession.beginTransaction();
			
			Query query =  hibernateSession.createQuery("from StorageTC where tc_id= :tc_id").setParameter("tc_id", tc);
			StorageTC storageTC = (StorageTC) query.getSingleResult();
			storageTC = (StorageTC) hibernateSession.load(StorageTC.class, new Integer(storageTC.getId()));
			sPattern = "main_" + sTestCase + ".t"; // sPattern = "main_912SC.t"
			lsFullPath = WorkingWithStings.getMatchingStrings(listOfFiles, sPattern);

			if (lsFullPath.size() > 0) {
				sFullPath = lsFullPath.get(0);
				sTestCaseNameInSilkTest = "main()";

			} else {
				sPattern = "\\\\" + sTestCase + ".t"; // sPattern = "999IS.t"
				lsFullPath = WorkingWithStings.getMatchingStrings(listOfFiles, sPattern);

				if (lsFullPath.size() > 0) {
					sFullPath = lsFullPath.get(0);
					sTestCaseNameInSilkTest = "TQC_" + sTestCase;

				} else {
					sPattern = sIssueNumber + ".t"; // sPattern = "206890SC.t"
					lsFullPath = WorkingWithStings.getMatchingStrings(listOfFiles, sPattern);

					if (lsFullPath.size() > 0) {
						sFullPath = lsFullPath.get(0);
						sTestCaseNameInSilkTest = "TQC_" + sTestCase;
					}
					else{
						
					 //With duration 0 and Rules are not participated in logic	

					}
				}	
			}
			
			storageTC.setRun_param(sTestCaseNameInSilkTest);
			storageTC.setRun_path(sFullPath); ;

			hibernateSession.update(storageTC);
			tx.commit();
			
		}

	}

}
