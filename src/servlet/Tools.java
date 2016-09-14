package servlet;

import java.io.IOException;

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

import com.google.gson.Gson;

import async.actions.AsyncMailProcessor;
import async.listener.AppAsyncListener;
import models.entities.User;
import utils.ChartFormationJFreeChart;
import utils.DataFromCurrentTestingTableDB;
import utils.Mail;
import utils.Utils;

@WebServlet(name = "Tools", urlPatterns = "/tools", asyncSupported = true)
public class Tools extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		List testings = hibernateSession.createQuery("from Testing").getResultList();
		tx.commit();

		request.setAttribute("testings", testings);

		request.setAttribute("title", "Tools");
		request.setAttribute("template", "tools.jsp");
		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			response.setStatus(400);
			response.getWriter().println("error: action is missing!!!");
			return;
		}

		if (action.equals("sendmail")) {
			String testingId = request.getParameter("testing_id");
			String verTQC = request.getParameter("tqc_version");
			String officialEnv = request.getParameter("official_env");
			String PathFile = getServletContext().getRealPath("pie_chart.png");
			
			// Getting data (duration and count of TC) from current testing
			// table
			DataFromCurrentTestingTableDB data = Utils.GetDurationAndCountTCFromDB(request,	Integer.parseInt(testingId));
			// response.getWriter().println((new Gson()).toJson(data));
			Long CountFailedTC = data.getCountFailedTC();
			Long CountPassedTC = data.getCountPassedTC();
			Long DurationFailedTC = data.getDurationFailedTC();
			Long DurationPassedTC = data.getDurationPassedTC();
			Long CountTotalTC = data.getCountTotalTC();
			Long DurationTotalTC = data.getDurationTotalTC();
			
			Long CountProcessedTC = CountPassedTC + CountFailedTC;
			Long DurationProcessedTC = DurationPassedTC + DurationFailedTC;
			int PercentOfCountProcessedTC = (int) ((CountProcessedTC * 100) / CountTotalTC);
			int PercentOfDurationProcessedTC = (int) ((DurationProcessedTC * 100) / DurationTotalTC);
			
			// Chart formation
			ChartFormationJFreeChart chart = new ChartFormationJFreeChart(data, PathFile);
			chart.createChart();
			
			//Mail sending
			Mail mail = new Mail();
			mail.setPathFile(PathFile);
			mail.setAddressTo(new String[] { "deko@isd.dp.ua" });
			mail.setAddressCc(new String[] { "deko@isd.dp.ua" });
			mail.setSubjectText("Current statistics for testing on " + officialEnv + "(TQC version: " + verTQC + ")");
			mail.setBodyText("<html> " + "<body> "
					+ "<H3>Hello!! Here you can find current statistics for testing of SoftTotalQC: </H3>"
					+ "<b>Testing progress:</b>" + "<br>" + "<b>- total TCs: [" + CountTotalTC + "] - ["
					+ DurationTotalTC + " min]</b>" + "<br>" + "<b>- processed TCs: [" + CountProcessedTC + " - "
					+ PercentOfCountProcessedTC + "%] [" + DurationProcessedTC + " min - "
					+ PercentOfDurationProcessedTC + "%]</b>" + "<br><br>" + "<table border='0'>" + "<tr>"
					+ "<td><div style='float: left'><img src=\"cid:image\"></div></td>" + "</tr>" + "</table>"
					+ "</body>" + "</html>");
			
			request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
			AsyncContext asyncCtx = request.startAsync();
			asyncCtx.addListener(new AppAsyncListener());
			asyncCtx.setTimeout(1 * 60000); // 1 minutes
			ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");
			executor.execute(new AsyncMailProcessor(asyncCtx, mail));

		}
	}

}
