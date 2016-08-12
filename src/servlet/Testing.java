package servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.Column;
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
import com.google.gson.GsonBuilder;

import utils.TestingSerializer;
import models.entities.TestingSheet;
import models.entities.TestSet;

@WebServlet("/testing")
public class Testing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Gson gson = new GsonBuilder().registerTypeAdapter(models.entities.Testing.class, new TestingSerializer()).create();
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();

		List<models.entities.Testing> testings = hibernateSession.createQuery("from Testing").getResultList();		
		List users = hibernateSession.createQuery("from User").getResultList();	
		tx.commit();		
		
		List<TestingSheet> testSheet = getTestingSheet(Integer.valueOf(7), "all");
		

		request.setAttribute("title", "Testing");
		request.setAttribute("users", users);
		request.setAttribute("testings", testings);
		request.setAttribute("testSheet", gson.toJson(testSheet));		

		request.setAttribute("template", "testing.jsp");
		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String action = request.getParameter("action");
		// get parameters

		String runner = request.getParameter("runner");
		String testingId = request.getParameter("testingId");
		// edit parameters
		String id = request.getParameter("id");
		String runnerEdt = request.getParameter("runnerEdt");
		String tcStatusEdt = request.getParameter("tcStatusEdt");
		String durationEdt = request.getParameter("durationEdt");
		String commentEdt = request.getParameter("commentEdt");
		String tqcVerEdt = request.getParameter("tqcVerEdt");
		String labVerEdt = request.getParameter("labVerEdt");
		String geneVerEdt = request.getParameter("geneVerEdt");

		if (action == null) {
			response.setStatus(400);
			response.getWriter().println("error: action is missing");
			return;
		}

		if (action.equals("get")) {

			if (runner == null || testingId == null) {
				response.setStatus(400);
				response.getWriter().println("error: please define user and desired testing!");
				return;
			}

			List<TestingSheet> testSheet = getTestingSheet(Integer.valueOf(testingId), runner);
			Gson gson = new GsonBuilder().registerTypeAdapter(models.entities.Testing.class, new TestingSerializer()).create();
			response.getWriter().println(gson.toJson(testSheet));
		}

		if (action.equals("edit")) {

			if (id == null) {
				response.setStatus(400);
				response.getWriter().println("error: id is missing");
				return;
			}
			SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
			Session hibernateSession = sessionFactory.getCurrentSession();
			Transaction tx;

			tx = hibernateSession.beginTransaction();
			TestingSheet testingSheet = (TestingSheet) hibernateSession.load(TestingSheet.class, new Integer(id));

			if (runnerEdt != null) {
				testingSheet.setRunner(runnerEdt);
			}
			if (tcStatusEdt != null) {
				testingSheet.setTcStatus(tcStatusEdt);
			}
			if (durationEdt != null) {
				testingSheet.setDuration(new Integer(durationEdt));
			}
			if (commentEdt != null) {
				testingSheet.setComment(commentEdt);
			}
			if (tqcVerEdt != null) {
				testingSheet.setTqcVer(tqcVerEdt);
			}
			if (labVerEdt != null) {
				testingSheet.setLabVer(labVerEdt);
			}
			if (geneVerEdt != null) {
				testingSheet.setGeneVer(geneVerEdt);
			}

			hibernateSession.update(testingSheet);
			tx.commit();
		}
	}

	private List<TestingSheet> getTestingSheet(int testingId, String runner) {

		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		Query<TestingSheet> query = null;

		if (runner.toLowerCase().equals("all")) {
			query = hibernateSession.createQuery("SELECT DISTINCT tsh FROM TestingSheet tsh LEFT JOIN FETCH tsh.testing LEFT JOIN FETCH tsh.storageTC stc LEFT JOIN FETCH stc.testSet WHERE tsh.testingId = :testingId")
					.setParameter("testingId", testingId);
		} else {

			query = hibernateSession
					.createQuery("SELECT DISTINCT tsh FROM TestingSheet tsh LEFT JOIN FETCH tsh.storageTC stc LEFT JOIN FETCH stc.testSet WHERE tsh.testingId = :testingId AND tsh.runner = :runner")
					.setParameter("testingId", testingId).setParameter("runner", runner);

		}
		List<TestingSheet> testSheet = query.getResultList();

		tx.commit();

		return testSheet;

	}

}
