package servlet.auth;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import models.entities.User;
import utils.Ldap;

@WebServlet("/loginpage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			request.setAttribute("logged", session.getAttribute("user"));
		}

		request.getRequestDispatcher("/WEB-INF/tpls/login-page.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		String login = request.getParameter("login");
		String passw = request.getParameter("passw");

		if (action.equals("Logout")) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect("/tracker2/loginpage");
			return;
		}

		try {

			// throws AuthenticationException if credentials are invalid
			Ldap ldap = new Ldap(login, passw);
			User ldapUser = ldap.search(login);

			SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
			Session hibernateSession = sessionFactory.getCurrentSession();
			Transaction tx = hibernateSession.beginTransaction();
			User dbUser = hibernateSession.get(User.class, ldapUser.getId());
			tx.commit();

			if (dbUser != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", dbUser);
				// session will expire in 30 days
				session.setMaxInactiveInterval(30 * 24 * 60 * 60);
			}

			response.sendRedirect("/tracker2/loginpage");

		} catch (AuthenticationException e) {

			e.printStackTrace();
			if (e.getMessage().contains("error code 49")) {
				response.getWriter().println("Invalid credentials");
			} else {
				response.getWriter().println(e.getMessage().trim());
			}

		} catch (NamingException e) {

			e.printStackTrace();
			e.printStackTrace(response.getWriter());

		}

	}

}
