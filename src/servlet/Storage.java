package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import models.entities.StorageTC;
import utils.Utils;

@WebServlet("/storage")
public class Storage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Storage.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		List<StorageTC> tcs = hibernateSession.createQuery("SELECT DISTINCT stc FROM StorageTC stc LEFT JOIN FETCH stc.testSet").getResultList();
		List users = hibernateSession.createQuery("from User").getResultList();
		List testsets = hibernateSession.createQuery("from TestSet").getResultList();
		tx.commit();

		request.setAttribute("title", "Storage");

		request.setAttribute("tcs", gson.toJson(tcs));
		request.setAttribute("jusers", gson.toJson(users));
		request.setAttribute("users", users);
		request.setAttribute("testsets", testsets);

		request.setAttribute("template", "storage.jsp");
		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Gson gson = new Gson();
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		String editMsg, addMsg;

		if (action == null) {
			response.setStatus(400);
			response.getWriter().println("error: action is missing");
			return;
		}

		String authorEdt = request.getParameter("edt_author");
		String stepNumEdt = request.getParameter("edt_step_num");
		String durationEdt = request.getParameter("edt_duration");
		String featuresEdt = request.getParameter("edt_features");
		String runPathEdt = request.getParameter("edt_run_path");
		String runParamEdt = request.getParameter("edt_run_param");
		String apps = request.getParameter("apps");
		String tags = request.getParameter("tags");

		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();
		Transaction tx;

		if (action.equals("edit")) {

			if (id == null) {
				response.setStatus(400);
				response.getWriter().println("error: id is missing");
				return;
			}

			tx = hibernateSession.beginTransaction();
			StorageTC storageTC = (StorageTC) hibernateSession.load(StorageTC.class, new Integer(id));

			editMsg = "<< TC-" + storageTC.getTc_id() + " >>";

			if (authorEdt != null) {
				storageTC.setAuthor(authorEdt);
				editMsg = editMsg + " New author: " + authorEdt + " || ";
			}
			if (apps != null) {
				storageTC.setApps(apps);
				editMsg = editMsg + " New apps: " + apps + " || ";
			}
			if (tags != null) {
				storageTC.setTags(tags);
				editMsg = editMsg + " New tags: " + tags + " || ";
			}
			if (featuresEdt != null) {
				storageTC.setFeatures(featuresEdt);
				editMsg = editMsg + " New features: " + featuresEdt + " || ";
			}
			if (runPathEdt != null) {
				storageTC.setRun_path(runPathEdt);
				editMsg = editMsg + " New run path: " + runPathEdt + " || ";
			}
			if (runParamEdt != null) {
				storageTC.setRun_param(runParamEdt);
				editMsg = editMsg + " New run param: " + runParamEdt + " || ";
			}
			if (stepNumEdt != null) {
				storageTC.setStep_num(new Integer(stepNumEdt));
				editMsg = editMsg + " New step number: " + stepNumEdt + " || ";
			}
			if (durationEdt != null) {
				storageTC.setDuration(new Integer(durationEdt));
				editMsg = editMsg + " New duration: " + durationEdt + " || ";
			}
			hibernateSession.update(storageTC);
			tx.commit();

			response.getWriter().println(gson.toJson(utils.Utils.unproxy(storageTC)));

			Utils.LogMessage(logger, editMsg, request);

		} else if (action.equals("add")) {

			StorageTC stc = new StorageTC();

			String tcId = request.getParameter("edt_tc_id");
			String testSet = request.getParameter("edt_test_set");
			String autoIde = request.getParameter("edt_auto_ide");
			stc.setTc_id(tcId);
			stc.setAuthor(authorEdt);
			stc.setAuto_ide(autoIde);
			stc.setTestSetId(new Integer(testSet));
			stc.setStep_num(new Integer(stepNumEdt));
			stc.setDuration(new Integer(durationEdt));

			if (apps != null) {
				stc.setApps(apps);
			}
			if (tags != null) {
				stc.setTags(tags);
			}
			if (featuresEdt != null) {
				stc.setFeatures(featuresEdt);
			}
			if (runPathEdt != null) {
				stc.setRun_path(runPathEdt);
			}
			if (runParamEdt != null) {
				stc.setRun_param(runParamEdt);
			}
			tx = hibernateSession.beginTransaction();
			hibernateSession.save(stc);
			tx.commit();

			hibernateSession = sessionFactory.getCurrentSession();
			tx = hibernateSession.beginTransaction();
			Object addedTc = hibernateSession.createQuery("SELECT DISTINCT stc FROM StorageTC stc LEFT JOIN FETCH stc.testSet where stc.id = :id").setParameter("id", stc.getId()).getSingleResult();
			tx.commit();

			response.getWriter().println(gson.toJson(addedTc));

			addMsg = "<< TC-" + stc.getTc_id() + " >> has benn added";
		}

	}

}
