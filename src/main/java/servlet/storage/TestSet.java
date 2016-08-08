package servlet.storage;

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

import models.entities.StorageTC;

@WebServlet("/testset")
public class TestSet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		List<StorageTC> tcs = hibernateSession.createQuery("from TestSet").getResultList();
		tx.commit();

		response.getWriter().println(gson.toJson(tcs));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String localSet = request.getParameter("local_set");
		String sdSet = request.getParameter("sd_set");

		if (localSet == null || sdSet == null) {
			response.getWriter().println("error: local_set or sd_set is missing");
			return;
		}

		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		models.entities.TestSet testSet = new models.entities.TestSet();
		testSet.setLocalSet(localSet);
		testSet.setSdSet(sdSet);

		Transaction tx = hibernateSession.beginTransaction();
		hibernateSession.save(testSet);
		tx.commit();

		Gson gson = new Gson();
		response.getWriter().println(gson.toJson(testSet));

	}

}
