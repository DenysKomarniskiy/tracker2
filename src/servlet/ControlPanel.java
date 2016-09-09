package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.scc.softdev.services.TTestCase;
import com.scc.softdev.services.impl.SDException;
import com.scc.softdev.services.impl.SoftDevTestCase;
import com.scc.softdev.services.impl.TestCaseImplService;

import models.entities.StorageTC;
import models.entities.TestingSheet;
import models.entities.User;

import models.entities.Testing;
import utils.Distributor;
import utils.HeaderHandlerResolver;

@WebServlet("/controlpanel")
public class ControlPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("testing_id"));



	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action.equals("generate")) {
			generateTesting(request, response);
			return;
		} else if(action.equals("delete")){
			
			String testingId = request.getParameter("testing_id");
			deleteTesting(request, Integer.valueOf(testingId));
			response.sendRedirect("/tracker2/tools");
			return;
		}


	}
	
	private void deleteTesting(HttpServletRequest request, int testingId) throws IOException{
		
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();
		Transaction tx = hibernateSession.beginTransaction();
		hibernateSession
			.createQuery("DELETE FROM TestingSheet tsh WHERE tsh.testingId= :testingId")
			.setParameter("testingId", testingId)
			.executeUpdate();
		hibernateSession
			.createQuery("DELETE FROM Testing tst WHERE tst.id= :id")
			.setParameter("id", testingId)
			.executeUpdate();		
		tx.commit();		
	}
	
	private void generateTesting(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String testingName = request.getParameter("testing_name");
		
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();
		List<TestingSheet> newTestingSheet = new ArrayList<TestingSheet>();
		Map<String, List<Long>> timePerUser = new HashMap<String, List<Long>>();
		List<Long> newTCcountTCduration = null, oldTCcountTCduration = null;

		String testCompleteRunner = "rovo";
		String userWithMinTime = null;

		Testing testing = new Testing();
		testing.setName(testingName);

		Transaction tx = hibernateSession.beginTransaction();
		List<StorageTC> allTestComplTCs = hibernateSession.createQuery("SELECT DISTINCT stc FROM StorageTC stc LEFT JOIN FETCH stc.testSet WHERE stc.auto_ide = :ide").setParameter("ide", "TC").getResultList();
		List<StorageTC> allOtherTCs = hibernateSession.createQuery("SELECT DISTINCT stc FROM StorageTC stc LEFT JOIN FETCH stc.testSet WHERE stc.auto_ide != :ide ").setParameter("ide", "TC").getResultList();
		Long totalTime = (Long) hibernateSession.createQuery("SELECT sum(stc.duration) FROM StorageTC stc").getSingleResult();
		Long testCompCount = (Long) hibernateSession.createQuery("SELECT count(*) FROM StorageTC stc WHERE stc.auto_ide = :ide").setParameter("ide", "TC").getSingleResult();
		Long testCompDuration = (Long) hibernateSession.createQuery("SELECT sum(stc.duration) FROM StorageTC stc WHERE stc.auto_ide = :ide").setParameter("ide", "TC").getSingleResult();
		List<User> users = hibernateSession.createQuery("FROM User U").getResultList();
		tx.commit();

		Collections.shuffle(allOtherTCs, new Random(System.nanoTime()));

		for (User user : users) {
			newTCcountTCduration = new ArrayList<Long>();
			newTCcountTCduration.add((long) 0);
			newTCcountTCduration.add((long) 0);
			timePerUser.put(user.getId(), newTCcountTCduration);
		}

		newTCcountTCduration = new ArrayList<Long>();
		newTCcountTCduration.add(testCompCount);
		newTCcountTCduration.add(testCompDuration);
		timePerUser.put(testCompleteRunner, newTCcountTCduration);

		for (StorageTC storageTc : allTestComplTCs) {
			TestingSheet testingSheetEntry = new TestingSheet();
			testingSheetEntry.setTesting(testing);
			testingSheetEntry.setRunner(testCompleteRunner);
			testingSheetEntry.setDuration(storageTc.getDuration());
			testingSheetEntry.setStorageTC(storageTc);
			newTestingSheet.add(testingSheetEntry);
		}

		for (StorageTC storageTc : allOtherTCs) {
			TestingSheet testingSheetEntry = new TestingSheet();
			userWithMinTime = Distributor.getUserIdWithMinTime(timePerUser, new ArrayList<String>());
			testingSheetEntry.setTesting(testing);
			testingSheetEntry.setRunner(userWithMinTime);
			testingSheetEntry.setDuration(storageTc.getDuration());
			testingSheetEntry.setStorageTC(storageTc);

			oldTCcountTCduration = timePerUser.get(userWithMinTime);

			newTCcountTCduration = new ArrayList<Long>();
			newTCcountTCduration.add(oldTCcountTCduration.get(0) + 1);
			newTCcountTCduration.add(oldTCcountTCduration.get(1) + storageTc.getDuration());

			timePerUser.put(userWithMinTime, newTCcountTCduration);
			newTestingSheet.add(testingSheetEntry);
		}

		testing.setTestingSheet(newTestingSheet);

		hibernateSession = sessionFactory.getCurrentSession();
		tx = hibernateSession.beginTransaction();
		hibernateSession.save(testing);

		for (TestingSheet testingSheetEntry : newTestingSheet) {
			hibernateSession.save(testingSheetEntry);
		}
		tx.commit();

		Gson gson = new Gson();
		response.getWriter().println(gson.toJson(timePerUser));		
	}

}
