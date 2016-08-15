package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

		Gson gson = new Gson();
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		List testings = hibernateSession.createQuery("from Testing").getResultList();
		List users = hibernateSession.createQuery("from User").getResultList();
		List<StorageTC> tcs = hibernateSession.createQuery("SELECT DISTINCT stc FROM StorageTC stc LEFT JOIN FETCH stc.testSet").getResultList();
		tx.commit();

		Map<String, String> cook =  utils.Utils.getCookiesMap(request.getCookies());
		
		request.setAttribute("title", "Storage");
		request.setAttribute("users", users);
		request.setAttribute("selectedUser", cook.get("user_id"));
		request.setAttribute("selectedTesting", cook.get("testing_id"));	
		request.setAttribute("jusers", gson.toJson(users));
		request.setAttribute("testings", testings);
		request.setAttribute("tcs", gson.toJson(tcs));

		request.setAttribute("template", "storage.jsp");
		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	

	}

}
