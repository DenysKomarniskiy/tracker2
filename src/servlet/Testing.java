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

import com.google.gson.Gson;


@WebServlet("/testingsheet")
public class Testing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Gson gson = new Gson();
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		List tcs = hibernateSession
				.createQuery("SELECT DISTINCT ts FROM TestingSheet ts LEFT JOIN FETCH ts.storageTC stc LEFT JOIN FETCH stc.testSet WHERE ts.testingId = :testing_id AND ts.runner = :runner")
				.setParameter("testing_id", 1)
				.setParameter("runner", "opya")
				.getResultList();
		tx.commit();

		response.getWriter().println(gson.toJson(tcs));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
