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

import utils.ChartFormationJFreeChart;
import utils.DataFromCurrentTestingTableDB;
import utils.Mail;
import utils.Utils;

/**
 * Servlet implementation class Tools
 */
@WebServlet("/tools")
public class Tools extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		

		if (action == null) {
			response.setStatus(400);
			response.getWriter().println("error: action is missing!!!");
			return;
		}

		if (action.equals("Send Mail")) {
			String testingId = request.getParameter("testing_id");
			String PathFile = getServletContext().getRealPath("pie_chart.png");
System.out.println("Testing ID is:" + testingId);
			// Getting data (duration and count of TC) from current testing table
			DataFromCurrentTestingTableDB data = Utils.GetDurationAndCountTCFromDB(request, (int) 8);
//			response.getWriter().println((new Gson()).toJson(data));
			
			
			String labelPassed = "Passed [" + data.getDurationPassedTC() + " min]";
			String labelFailed = "Failed [" + data.getDurationFailedTC() + " min]";
			String labelWaiting = "Waiting [" + data.getDurationWaitingTC() + " min]";
			String labelCorrection = "Correction [" + data.getDurationCorrectionTC() + " min]";
			String labelNoNeed = "NoNeed [" + data.getDurationNoNeedTC() + " min]";
			String labelEmpty = "Empty [" + data.getDurationEmptyTC() + " min]";
			String labelInvestigating = "Investigating [" + data.getDurationInvestigatingTC() + " min]";
			
			Long CountPassedTC =  data.getCountPassedTC();
			Long CountFailedTC =  data.getCountFailedTC();
			Long CountWaitingTC =  data.getCountWaitingTC();
			Long CountCorrectionTC =  data.getCountCorrectionTC();
			Long CountNoNeedTC =  data.getCountNoNeedTC();
			Long CountEmptyTC =  data.getCountEmptyTC();
			Long CountInvestigatingTC =  data.getCountInvestigatingTC();
			
			ChartFormationJFreeChart chart = new ChartFormationJFreeChart();
			
			chart.setPathFile(PathFile);
			chart.setListOflabel(new String[]{labelPassed, labelFailed, labelWaiting, labelCorrection, labelNoNeed, labelEmpty, labelInvestigating});
			chart.setListOfCountTC(new Long[]{CountPassedTC, CountFailedTC, CountWaitingTC, CountCorrectionTC, CountNoNeedTC, CountEmptyTC, CountInvestigatingTC});
			chart.createChart();
			
			System.out.println(PathFile);

			Mail mail = new Mail();
			mail.setPathFile(PathFile);
			mail.setAddressFrom("tqc.autobot@isd.dp.ua");
			mail.setAddressTo(new String[]{ "deko@isd.dp.ua" });
			mail.setAddressCc(new String[]{ "deko@isd.dp.ua" });
			mail.setSubjectText("Current statistics for testing on zw10_pt92(TQC version: 1.0.5.0.6)");
			mail.setBodyText("<html> " + 
					"<body> " + 
					"<H3>Hello!! Here you can find current statistics for testing on lv66(TQC version: 1.0.4.0.1): </H3>" + 
					"<b>Testing progress:</b>" +
					"<br>" +
					"<b>- total TCs: [720] - [34166 min]</b>" +
					"<br>" +
					"<b>- processed TCs: [710 - 98%] [33447 min - 97%]</b>" +
					"<br><br>" +
					"<table border='0'>" + 
					"<tr>" + 
					"<td><div style='float: left'><img src=\"cid:image\"></div></td>" + 
					"</tr>" + 
					"</table>" + 
					"</body>" + 
					"</html>");
			try {
				mail.send();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.getWriter().println("sendingMail working...");

			return;
		}
	}

}
