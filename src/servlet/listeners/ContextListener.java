package servlet.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import models.entities.StorageTC;
import models.entities.TestSet;
import models.entities.Testing;
import models.entities.TestingSheet;
import models.entities.User;

//import util.TestingTestcase;

@WebListener
public class ContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(StorageTC.class);
		configuration.addAnnotatedClass(Testing.class);
		configuration.addAnnotatedClass(TestingSheet.class);		
		configuration.addAnnotatedClass(TestSet.class);
		configuration.addAnnotatedClass(User.class);
		System.out.println("Hibernate Configuration created successfully");

		// logger.info("Hibernate Configuration created successfully");

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		// logger.info("ServiceRegistry created successfully");
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		// logger.info("SessionFactory created successfully");

		servletContextEvent.getServletContext().setAttribute("HibernateSessionFactory", sessionFactory);
		// logger.info("Hibernate SessionFactory Configured successfully");
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		SessionFactory sessionFactory = (SessionFactory) servletContextEvent.getServletContext().getAttribute("HibernateSessionFactory");
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			// logger.info("Closing sessionFactory");
			sessionFactory.close();
		}
		// logger.info("Released Hibernate sessionFactory resource");

	}

}
