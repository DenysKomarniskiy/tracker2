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
import org.hibernate.query.Query;

import com.google.gson.Gson;

@WebServlet("/testset")
public class TestSet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession = sessionFactory.getCurrentSession();
		Gson gson = new Gson();

		Transaction tx = hibernateSession.beginTransaction();
		List testsets = hibernateSession.createQuery("from TestSet").getResultList();
		tx.commit();

		request.setAttribute("title", "Test Sets management");
		request.setAttribute("template", "testset.jsp");
		request.setAttribute("testsets", gson.toJson(testsets));

		request.getRequestDispatcher("/WEB-INF/tpls/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("HibernateSessionFactory");
		Session hibernateSession;
		Transaction tx;

		if (action == null) {
			response.setStatus(400);
			response.getWriter().println("error: action is missing");
			return;
		}
		
		String id = request.getParameter("id");
		String localSet = request.getParameter("edt_local_set");
		String sdSet = request.getParameter("edt_sd_set");

		if (action.equals("add")) {		

			if (localSet == null || sdSet == null) {
				response.setStatus(400);
				response.getWriter().println("error: local_set or sd_set is missing");
				return;
			}
			
			hibernateSession = sessionFactory.getCurrentSession();
			models.entities.TestSet testSet = new models.entities.TestSet();
			testSet.setLocalSet(localSet);
			testSet.setSdSet(sdSet);

			tx = hibernateSession.beginTransaction();
			hibernateSession.save(testSet);
			tx.commit();

			Gson gson = new Gson();
			response.getWriter().println(gson.toJson(testSet));
			return;
		}

		if (action.equals("edit")) {			

			if (id == null) {
				response.setStatus(400);
				response.getWriter().println("error: please populate id");
				return;
			}
			
			hibernateSession = sessionFactory.getCurrentSession();
			tx = hibernateSession.beginTransaction();
			models.entities.TestSet testSet = (models.entities.TestSet) hibernateSession.createQuery("from TestSet where id= :id").setParameter("id", Integer.valueOf(id)).getResultList().stream()
					.findFirst().orElse(null);
			tx.commit();

			if (testSet == null) {
				response.getWriter().println("error: No such id");
				return;
			}


			if (localSet == null && sdSet == null) {
				response.setStatus(400);
				response.getWriter().println("error: local_set and sd_set is emty, nothing  to modify");
				return;
			}	
			
			hibernateSession = sessionFactory.getCurrentSession();
			tx = hibernateSession.beginTransaction();
			Query query = null;
			
			if (localSet != null && sdSet != null) {				
				query = hibernateSession
						.createQuery("update TestSet set local_set=:local_set, sd_set=:sd_set  where id= :id")
						.setParameter("sd_set", sdSet)
						.setParameter("local_set", localSet)
						.setParameter("id", Integer.valueOf(id));				
			} else if(sdSet != null){
				query = hibernateSession
						.createQuery("update TestSet set sd_set=:sd_set where id= :id")
						.setParameter("sd_set", sdSet)
						.setParameter("id", Integer.valueOf(id));
			} else if (localSet != null) {
				query = hibernateSession
						.createQuery("update TestSet set local_set=:local_set where id= :id")
						.setParameter("local_set", localSet)
						.setParameter("id", Integer.valueOf(id));						
			}					
			
			int result = query.executeUpdate();
			tx.commit();

			response.getWriter().println("Test_set Update Status=" + result);
			return;
		}

		if (action.equals("delete")) {			
			
			if (id == null) {
				response.setStatus(400);
				response.getWriter().println("error: please populate id");
				return;
			}			
			
			hibernateSession = sessionFactory.getCurrentSession();
			tx = hibernateSession.beginTransaction();
			models.entities.TestSet testSet = (models.entities.TestSet) hibernateSession.createQuery("from TestSet where id= :id").setParameter("id", Integer.valueOf(id)).getResultList().stream()
					.findFirst().orElse(null);
			tx.commit();

			if (testSet == null) {
				response.getWriter().println("error: No such id");
				return;
			}
			
			hibernateSession = sessionFactory.getCurrentSession();
			tx = hibernateSession.beginTransaction();
			int result = hibernateSession.createQuery("delete from TestSet where id= :id").setParameter("id", Integer.valueOf(id)).executeUpdate();
			tx.commit();

			response.getWriter().println("Test_set Delete Status=" + result);
			return;

		   	
			
		}

	}

}
