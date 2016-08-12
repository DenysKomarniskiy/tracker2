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

import utils.TestingSerializer;
import models.entities.TestingSheet;

@WebServlet("/testing")
public class Testing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Gson gson = new GsonBuilder().registerTypeAdapter(models.entities.Testing.class, new TestingSerializer()).create();
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();		

		Transaction tx = hibernateSession.beginTransaction();
		List<models.entities.Testing> testings = hibernateSession.createQuery("from Testing").getResultList();
		int lastTestingId = testings.get(testings.size()-1).getId();
		List users = hibernateSession.createQuery("from User").getResultList();	
		tx.commit();
		
		

		request.setAttribute("title", "Testing");
		request.setAttribute("users", users);
		request.setAttribute("testings", testings);
		request.setAttribute("testingSheet", lastTestingId);

		request.setAttribute("template", "testing.jsp");
		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);

//		
//		 List<models.entities.Testing> testing = getTestingSheet(7, "opya");
//		
//		 response.getWriter().println(gson.toJson(testing));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String runner = request.getParameter("runner");
		String testingId = request.getParameter("testingId");

		if (runner == null || testingId == null) {
			response.setStatus(400);
			response.getWriter().println("error: please define user and testing!");
			return;
		}

		List<TestingSheet> testSheet = getTestingSheet(Integer.valueOf(testingId), runner);

		Gson gson = new GsonBuilder().registerTypeAdapter(models.entities.Testing.class, new TestingSerializer()).create();
		response.getWriter().println(gson.toJson(testSheet));
	}

	private List<TestingSheet> getTestingSheet(int testingId, String runner) {

		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		Query<TestingSheet> query = null;

		if (runner.toLowerCase().equals("all")) {
			query = hibernateSession.createQuery("SELECT DISTINCT tsh FROM TestingSheet tsh LEFT JOIN FETCH tsh.storageTC stc LEFT JOIN FETCH stc.testSet WHERE tsh.testingId = :testingId")
					.setParameter("testingId", testingId);
		} else {
			query = hibernateSession.createQuery("SELECT DISTINCT tsh FROM TestingSheet tsh LEFT JOIN FETCH tsh.storageTC stc LEFT JOIN FETCH stc.testSet WHERE tsh.testingId = :testingId AND tsh.runner = :runner")
					.setParameter("testingId", testingId)
					.setParameter("runner", runner);
		}
		List<TestingSheet> testSheet = query.getResultList();

		tx.commit();

		return testSheet;

	}

}
