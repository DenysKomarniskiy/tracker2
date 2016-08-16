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
import models.entities.TestingSheet;


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
		List<StorageTC> tcs = hibernateSession.createQuery("SELECT DISTINCT stc FROM StorageTC stc LEFT JOIN FETCH stc.testSet").getResultList();
		List users = hibernateSession.createQuery("from User").getResultList();
		tx.commit();
		
		request.setAttribute("title", "Storage");

		request.setAttribute("tcs", gson.toJson(tcs));
		request.setAttribute("jusers", gson.toJson(users));

		request.setAttribute("template", "storage.jsp");
		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		String id = request.getParameter("id");
		
		
		String authorEdt   = request.getParameter("edt_author");
		String featuresEdt = request.getParameter("edt_features");
		String runPathEdt = request.getParameter("edt_run_path");
		String runParamEdt = request.getParameter("edt_run_param");
		String stepNumEdt = request.getParameter("edt_step_num");
		String durationEdt = request.getParameter("edt_tduration");
		
		if (action == null) {
			response.setStatus(400);
			response.getWriter().println("error: action is missing");	
			return;
		}
		
		if (action.equals("edit")) {

			
			
			
			if (id == null) {
				response.setStatus(400);
				response.getWriter().println("error: id is missing");
				return;
			}
			SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
			Session hibernateSession = sessionFactory.getCurrentSession();
			Transaction tx;

			tx = hibernateSession.beginTransaction();
			StorageTC storageTC = (StorageTC) hibernateSession.load(StorageTC.class, new Integer(id));

			if (authorEdt != null) {
				storageTC.setAuthor(authorEdt);
			}
			if (featuresEdt != null) {
				storageTC.setFeatures(featuresEdt);
			}
			if (runPathEdt != null) {
				storageTC.setRun_path(runPathEdt);
			}
			if (runParamEdt != null) {
				storageTC.setRun_param(runParamEdt);
			}
			if (stepNumEdt != null) {
				storageTC.setStep_num(new Integer(stepNumEdt));
			}
			if (durationEdt != null) {
				storageTC.setDuration(new Integer(durationEdt));
			}
			hibernateSession.update(storageTC);
			tx.commit();
		}
		
		

	}

}
