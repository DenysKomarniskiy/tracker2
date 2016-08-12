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

		List<models.entities.Testing> testing = getTestingSheet(7, "opya");

		response.getWriter().println(gson.toJson(testing));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("user");
		String testingId = request.getParameter("testingId");
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession;
		Transaction tx;

		if (user == null || testingId == null) {
			response.setStatus(400);
			response.getWriter().println("error: please define user and testing!");
			return;
		}

		hibernateSession = sessionFactory.getCurrentSession();
		tx = hibernateSession.beginTransaction();
		Query<TestingSheet> query = null;

		if (user.toLowerCase().equals("all")) {
			query = hibernateSession.createQuery("from TestingSheet where testingId= :testingId").
					setParameter("testingId", Integer.valueOf(testingId));
		} else {
			query = hibernateSession.createQuery("from TestingSheet where testingId= :testingId and runner= :runner").
					setParameter("testingId", Integer.valueOf(testingId)).
					setParameter("runner", user);
		}
		List<TestingSheet> testSheet = query.getResultList();
		tx.commit();

		Gson gson = new GsonBuilder().registerTypeAdapter(models.entities.Testing.class, new TestingSerializer()).create();
		response.getWriter().println(gson.toJson(testSheet));
	}

	private List<models.entities.Testing> getTestingSheet(int testingId, String runner) {

		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		List<models.entities.Testing> testing = hibernateSession
				.createQuery("SELECT DISTINCT tsh FROM TestingSheet tsh LEFT JOIN FETCH tsh.storageTC stc LEFT JOIN FETCH stc.testSet WHERE tsh.testingId = :testingId AND tsh.runner = :runner")
				.setParameter("testingId", testingId)
				.setParameter("runner", runner)
				.getResultList();
		tx.commit();

		return testing;

	}

}
