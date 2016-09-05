package servlet;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;


import utils.ChartFormationJFreeChart;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

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
		
		String PathFile = request.getSession().getServletContext().getRealPath("pie_chart.png");
		System.out.println(PathFile);
		//String PathFile = (String) getServletContext().getAttribute("FILES_DIR");
		Mail mail = new Mail();
		//mail.setPathFile(PathFile + "\\pie_chart.png");
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
				"<b>- processed TCs: [712 - 98%] [33447 min - 97%]</b>" +
				"<br><br>" +
				"<table border='0'>" + 
					"<tr>" + 
						"<td><div style='float: left'><img src=\"cid:image\"></div></td>" + 
					"</tr>" + 
				"</table>" + 
			"</body>" + 
		"</html>");
		

		if (action == null) {
			response.setStatus(400);
			response.getWriter().println("error: action is missing!!!");
			return;
		}

		if (action.equals("Send Mail")) {

			ChartFormationJFreeChart chartFormationJFreeChart = new ChartFormationJFreeChart(PathFile);
			chartFormationJFreeChart.createChart();
//Utils.GetTotalTimeFromDB(request, response);

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
