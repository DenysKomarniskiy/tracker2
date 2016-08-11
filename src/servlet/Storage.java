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

import models.entities.StorageTC;


@WebServlet("/storage")
public class Storage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Storage() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Object to = request.getParameter("to");
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();
		
		if (to == null) {			
			Transaction tx = hibernateSession.beginTransaction();
			List testings = hibernateSession.createQuery("from Testing").getResultList();
			List users = hibernateSession.createQuery("from User").getResultList();			
			tx.commit();
			
			request.setAttribute("title", "Storage");
			request.setAttribute("template", "storage.jsp");
			request.setAttribute("users", users);
			request.setAttribute("testings", testings);

			request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);
			return;
		}

		if (to.equals("json")) {
			Gson gson = new Gson();			

			Transaction tx = hibernateSession.beginTransaction();
			List<StorageTC> tcs = hibernateSession.createQuery("SELECT DISTINCT stc FROM StorageTC stc LEFT JOIN FETCH stc.testSet").getResultList();
			tx.commit();

			response.getWriter().println(gson.toJson(tcs));
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}