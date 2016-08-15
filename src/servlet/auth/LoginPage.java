package servlet.auth;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@WebServlet("/loginpage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginPage() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		List users = hibernateSession.createQuery("from User").getResultList();
		List testings = hibernateSession.createQuery("from Testing tst ORDER BY tst.id DESC").getResultList();
		tx.commit();

		request.setAttribute("title", "Login to the system");		
		request.setAttribute("users", users);
		request.setAttribute("testings", testings);

		request.getRequestDispatcher("/WEB-INF/tpls/login-page.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie userCookie = new Cookie("runner", request.getParameter("user_id"));
		Cookie testingCookie = new Cookie("testing_id", request.getParameter("testing_id"));
		userCookie.setMaxAge(60*60*24*175);
		testingCookie.setMaxAge(60*60*24*175);
		response.addCookie(userCookie);
		response.addCookie(testingCookie);
		
		response.sendRedirect("/tracker2/testing");


	}

}

