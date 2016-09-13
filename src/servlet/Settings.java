package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.entities.User;

@WebServlet("/settings")
public class Settings extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("title", "Settings");

		Gson gson = new GsonBuilder().serializeNulls().create();

		request.setAttribute("juser", gson.toJson(request.getSession().getAttribute("user")));

		request.setAttribute("template", "settings.jsp");
		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Enumeration<String> params = request.getParameterNames();

		User user = (User) request.getSession().getAttribute("user");

		if (user == null)
			throw new ServletException("please re-login");

		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();
		Transaction tx;
		String hql = "update User set {columnName} = :columnVal where id = :id".replace("{columnName}", request.getParameter("id"));

		hibernateSession = sessionFactory.getCurrentSession();
		tx = hibernateSession.beginTransaction();
		int result = hibernateSession.createQuery(hql).setParameter("id", user.getId()).setParameter("columnVal", request.getParameter("val")).executeUpdate();
		User updatedUser = hibernateSession.get(User.class, user.getId());
		tx.commit();
		
		request.getSession().setAttribute("user", updatedUser);

		response.getWriter().println(result);

	}

}
