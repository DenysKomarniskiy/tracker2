package servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import async.actions.AsyncMailProcessor;
import async.actions.AsyncSoftDevProcessor;
import async.listener.AppAsyncListener;
import models.entities.Env;
import models.entities.TestingSheet;
import models.entities.User;
import utils.DataFromCurrentTestingTableDB;
import utils.Mail;
import utils.Rights;
import utils.TestingSerializer;
import utils.Utils;

@WebServlet(name = "Testing", urlPatterns = "/testing", asyncSupported = true)
public class Testing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(Testing.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Gson gson = new Gson();
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		List testings = hibernateSession.createQuery("SELECT tst.id, tst.name from Testing tst").getResultList();
		List users = hibernateSession.createQuery("from User WHERE active = :active").setParameter("active", 1).getResultList();
		List envs = hibernateSession.createQuery("from Env").getResultList();
		tx.commit();

		request.setAttribute("title", "Testing");

		request.setAttribute("jtestings", gson.toJson(testings));
		request.setAttribute("jusers", gson.toJson(users));
		request.setAttribute("jenvs", gson.toJson(envs));

		request.setAttribute("template", "testing.jsp");
		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		String runner = request.getParameter("user_id");
		String testingId = request.getParameter("testing_id");
		String logMsg;

		if (action == null) {
			response.setStatus(400);
			response.getWriter().println("error: action is missing");
			return;
		}

		Gson gson = new GsonBuilder().registerTypeAdapter(models.entities.Testing.class, new TestingSerializer()).create();
		User sessionUser = (User) request.getSession().getAttribute("user");
		
		if (action.equals("get")) {

			if (runner == null || testingId == null) {
				response.setStatus(400);
				response.getWriter().println("error: please define user and desired testing!");
				return;
			}

			List<TestingSheet> testSheet = getTestingSheet(Integer.valueOf(testingId), runner);

			response.getWriter().println(gson.toJson(testSheet));

			Utils.LogMessage(logger, "Testing Sheet is loaded for User:" + runner + " ||Testing id: " + testingId, request);

			return;

		} else if (action.equals("sdpost")) {

			SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
			Session hibernateSession = sessionFactory.getCurrentSession();
			Transaction tx;
			tx = hibernateSession.beginTransaction();
			TestingSheet sheetTc = (TestingSheet) hibernateSession
					.createQuery("SELECT DISTINCT tsh FROM TestingSheet tsh LEFT JOIN FETCH tsh.storageTC stc LEFT JOIN FETCH stc.testSet LEFT JOIN FETCH tsh.env LEFT JOIN FETCH tsh.testing WHERE tsh.id = :id")
					.setParameter("id", Integer.valueOf(request.getParameter("sheetentityid")))
					.getSingleResult();
			User user = (User) hibernateSession
					.createQuery("SELECT DISTINCT usr FROM User usr WHERE usr.id = :usr_id")
					.setParameter("usr_id", sheetTc.getRunner())
					.getSingleResult();
			tx.commit();
			
			if (!user.getId().equals(sessionUser.getId()) & ((sessionUser.getRights() & Rights.TS_EDIT) == 0))
				throw new ServletException("You have no rights");		

			if (user.getSdEncPass() == null)
				throw new ServletException("Please define SoftDev password");

			request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
			AsyncContext asyncCtx = request.startAsync();
			asyncCtx.addListener(new AppAsyncListener());
			asyncCtx.setTimeout(5 * 60000); // 5 minutes
			ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");
			executor.execute(new AsyncSoftDevProcessor(asyncCtx, sheetTc, user));

			Utils.LogMessage(logger, "<< TC-" + sheetTc.getStorageTC().getTc_id() + ">> has been posted in SoftDev with status " + sheetTc.getTcStatus(), request);

			return;

		} else if (action.equals("edit")) {

			String id = request.getParameter("id");
			String runnerEdt = request.getParameter("edt_runner");
			String tcStatusEdt = request.getParameter("edt_status");
			String durationEdt = request.getParameter("edt_tduration");
			String commentEdt = request.getParameter("edt_comment");
			String tqcVerEdt = request.getParameter("edt_tqc_ver");
			String labVerEdt = request.getParameter("edt_lab_ver");
			String geneVerEdt = request.getParameter("edt_gene_ver");
			String failInfo = request.getParameter("edt_fail_info");
			String envId = request.getParameter("edt_env_id");

			if (id == null) {
				response.setStatus(400);
				response.getWriter().println("error: id is missing");
				return;
			}
			SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
			Session hibernateSession = sessionFactory.getCurrentSession();
			Transaction tx;

			tx = hibernateSession.beginTransaction();
			TestingSheet testingSheet = (TestingSheet) hibernateSession.load(TestingSheet.class, new Integer(id));

			if (!testingSheet.getRunner().equals(sessionUser.getId()) & ((sessionUser.getRights() & Rights.TS_EDIT) == 0)) {
				tx.commit();
				throw new ServletException("You have no rights");
			}

			logMsg = "<< testsheet entry: " + testingSheet.getId() + " >>";

			if (runnerEdt != null) {
				User oldRunner = (User) hibernateSession.createQuery("from User where id = :id").setParameter("id", testingSheet.getRunner()).getSingleResult();
				User newRunner = (User) hibernateSession.createQuery("from User where id = :id").setParameter("id", runnerEdt).getSingleResult();

				SendMailAsync(request, testingSheet, oldRunner.getEmail(), newRunner.getEmail());
				testingSheet.setRunner(runnerEdt);
				logMsg += " New runner: " + runnerEdt + " || ";

			}
			if (tcStatusEdt != null) {
				testingSheet.setTcStatus(tcStatusEdt);
				logMsg += " New status: " + tcStatusEdt + " || ";
			}
			if (durationEdt != null) {
				testingSheet.setDuration(new Integer(durationEdt));
				logMsg += " New duration: " + durationEdt + " || ";
			}
			if (commentEdt != null) {
				testingSheet.setComment(commentEdt);
				logMsg += " New Comment: " + commentEdt + " || ";
			}
			if (tqcVerEdt != null) {
				testingSheet.setTqcVer(tqcVerEdt);
				logMsg += "New tqcver: " + tqcVerEdt + " || ";
			}
			if (labVerEdt != null) {
				testingSheet.setLabVer(labVerEdt);
				logMsg += " New labver: " + labVerEdt + " || ";
			}
			if (geneVerEdt != null) {
				testingSheet.setGeneVer(geneVerEdt);
				logMsg += " New genever: " + geneVerEdt + " || ";
			}
			if (failInfo != null) {
				testingSheet.setFailInfo(failInfo);
				logMsg += " New failInfo: " + failInfo + "|| ";
			}
			if (envId != null) {
				Env env = hibernateSession.get(Env.class, new Integer(envId));
				testingSheet.setEnv(env);
				logMsg += " New env: " + env.getName() + " || ";
			}

			hibernateSession.update(testingSheet);
			tx.commit();

			response.getWriter().println(gson.toJson(utils.Utils.unproxy(testingSheet)));
			Utils.LogMessage(logger, logMsg, request);

		}
	}

	private List<TestingSheet> getTestingSheet(int testingId, String runner) {

		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		Query<TestingSheet> query = null;

		if (runner.toLowerCase().equals("all")) {
			query = hibernateSession
					.createQuery(
							"SELECT DISTINCT tsh FROM TestingSheet tsh LEFT JOIN FETCH tsh.testing LEFT JOIN FETCH tsh.storageTC stc LEFT JOIN FETCH stc.testSet LEFT JOIN FETCH tsh.env WHERE tsh.testingId = :testingId")
					.setParameter("testingId", testingId);
		} else {

			query = hibernateSession
					.createQuery(
							"SELECT DISTINCT tsh FROM TestingSheet tsh LEFT JOIN FETCH tsh.testing LEFT JOIN FETCH tsh.storageTC stc LEFT JOIN FETCH stc.testSet LEFT JOIN FETCH tsh.env WHERE tsh.testingId = :testingId AND tsh.runner = :runner")
					.setParameter("testingId", testingId).setParameter("runner", runner);

		}
		List<TestingSheet> testSheet = query.getResultList();

		tx.commit();

		return testSheet;

	}

	private void SendMailAsync(HttpServletRequest request, TestingSheet testingSheet, String oldRunnerMail, String newRunnerMail) {

		System.out.println("oldRunnerMail " + oldRunnerMail);
		System.out.println("newRunnerMail " + newRunnerMail);

		String tcId = testingSheet.getStorageTC().getTc_id();
		String tcSet = testingSheet.getStorageTC().getTestSet().getLocalSet();

		Mail mail = new Mail("new_runner");
		mail.setSubjectText("New TC ( " + tcId + " from " + tcSet + " ) has been added to your Test Plan");
		mail.setAddressFrom(oldRunnerMail);
		mail.setAddressTo(new String[] { newRunnerMail });
		request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
		AsyncContext asyncCtx = request.startAsync();
		asyncCtx.addListener(new AppAsyncListener());
		asyncCtx.setTimeout(1 * 60000); // 1 minutes
		ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");
		executor.execute(new AsyncMailProcessor(asyncCtx, mail));
	}

}
