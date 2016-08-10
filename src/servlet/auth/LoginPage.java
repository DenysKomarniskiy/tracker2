package servlet.auth;

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

import models.entities.Testing;
import models.entities.User;

@WebServlet("/loginpage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginPage() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		List<User> users = hibernateSession.createQuery("from User").getResultList();
		List<Testing> testings = hibernateSession.createQuery("from Testing").getResultList();
		tx.commit();

		request.setAttribute("title", "Login to the system");
		request.setAttribute("template", "login-page.jsp");
		request.setAttribute("users", users);
		request.setAttribute("testings", testings);

		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("testing") != null) {

			response.getWriter().print("testing");

		}

		if (request.getParameter("storage") != null) {

		}

	}

}
