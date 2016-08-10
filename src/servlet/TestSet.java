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

@WebServlet("/testset")
public class TestSet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();
		Gson gson = new Gson();

		Transaction tx = hibernateSession.beginTransaction();
		List testsets = hibernateSession.createQuery("from TestSet").getResultList();
		List users = hibernateSession.createQuery("from User").getResultList();
		List testings = hibernateSession.createQuery("from Testing").getResultList();
		tx.commit();

		request.setAttribute("title", "Test Sets management");
		request.setAttribute("template", "testset.jsp");
		request.setAttribute("users", gson.toJson(users));
		request.setAttribute("testings", gson.toJson(testings));
		request.setAttribute("testsets", gson.toJson(testsets));

		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			response.getWriter().println("error: action is missing");
			return;
		}

		if (action.equals("add")) {

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
			return;
		}

		if (action.equals("edit")) {
			String id = request.getParameter("id");
			response.getWriter().println(id);
			return;
		}

		if (action.equals("delete")) {
			String id = request.getParameter("id");
			return;
		}

	}

}
