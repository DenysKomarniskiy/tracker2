package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.Gson;

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
		// TODO Auto-generated method stub
		
		
		
		
		request.setAttribute("title", "Tools");
		request.setAttribute("template", "tools.jsp");
		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Mail Mail = new Mail();
		
		
		if (action == null) {
			response.setStatus(400);
			response.getWriter().println("error: action is missing!!!");
			return;
		}
		
		
		if (action.equals("Sent Mail")) {
			response.getWriter().println("sendingMail working...");
			try {
				Mail.send();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			


			return;
		}
	}

}
