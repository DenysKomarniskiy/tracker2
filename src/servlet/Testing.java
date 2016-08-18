package servlet;

import java.io.IOException;
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
import com.google.gson.GsonBuilder;

import models.entities.TestingSheet;
import utils.TestingSerializer;

@WebServlet(name = "Testing", urlPatterns = "/testing")
public class Testing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Gson gson = new Gson();
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		List testings = hibernateSession.createQuery("SELECT tst.id, tst.name from Testing tst").getResultList();
		List users = hibernateSession.createQuery("from User").getResultList();
		tx.commit();		

		request.setAttribute("title", "Testing");

		request.setAttribute("jtestings", gson.toJson(testings));
		request.setAttribute("jusers", gson.toJson(users));

		request.setAttribute("template", "testing.jsp");
		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String runner = request.getParameter("user_id");
		String testingId = request.getParameter("testing_id");

		if (action == null) {
			response.setStatus(400);
			response.getWriter().println("error: action is missing");	
			return;
		}
		
		Gson gson = new GsonBuilder().registerTypeAdapter(models.entities.Testing.class, new TestingSerializer()).create();

		if (action.equals("get")) {

			if (runner == null || testingId == null) {
				response.setStatus(400);
				response.getWriter().println("error: please define user and desired testing!");
				return;
			}

			List<TestingSheet> testSheet = getTestingSheet(Integer.valueOf(testingId), runner);
			
			response.getWriter().println(gson.toJson(testSheet));

		} else if (action.equals("edit")) {

			// edit parameters
			String id = request.getParameter("id");
			String runnerEdt = request.getParameter("edt_runner");
			String tcStatusEdt = request.getParameter("edt_status");
			String durationEdt = request.getParameter("edt_tduration");
			String commentEdt = request.getParameter("edt_comment");
			String tqcVerEdt = request.getParameter("edt_tqc_ver");
			String labVerEdt = request.getParameter("edt_lab_ver");
			String geneVerEdt = request.getParameter("edt_gene_ver");
			
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
			
			Gson gson2 = new Gson();
			response.getWriter().println(gson2.toJson(testingSheet));
		}
	}

	private List<TestingSheet> getTestingSheet(int testingId, String runner) {

		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		Query<TestingSheet> query = null;

		if (runner.toLowerCase().equals("all")) {
			query = hibernateSession.createQuery("SELECT DISTINCT tsh FROM TestingSheet tsh LEFT JOIN FETCH tsh.testing LEFT JOIN FETCH tsh.storageTC stc LEFT JOIN FETCH stc.testSet WHERE tsh.testingId = :testingId").setParameter("testingId",
					testingId);
		} else {

			query = hibernateSession.createQuery("SELECT DISTINCT tsh FROM TestingSheet tsh LEFT JOIN FETCH tsh.testing LEFT JOIN FETCH tsh.storageTC stc LEFT JOIN FETCH stc.testSet WHERE tsh.testingId = :testingId AND tsh.runner = :runner")
					.setParameter("testingId", testingId).setParameter("runner", runner);

		}
		List<TestingSheet> testSheet = query.getResultList();

		tx.commit();

		return testSheet;

	}

}
