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
		List<models.entities.Testing> testing = hibernateSession
				.createQuery("SELECT DISTINCT tst FROM Testing tst LEFT JOIN FETCH tst.testingSheet tsh LEFT JOIN FETCH tsh.storageTC stc LEFT JOIN FETCH stc.testSet WHERE tst.id = :id ")
				.setParameter("id", 5)
				.getResultList();
		tx.commit();
		
		response.getWriter().println(gson.toJson(testing));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
