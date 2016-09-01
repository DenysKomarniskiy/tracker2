package servlet.listeners;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

@WebListener
public class ContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {

		// Initializing Hibernate Session
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(StorageTC.class);
		configuration.addAnnotatedClass(Testing.class);
		configuration.addAnnotatedClass(TestingSheet.class);
		configuration.addAnnotatedClass(TestSet.class);
		configuration.addAnnotatedClass(User.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		servletContextEvent.getServletContext().setAttribute("HibernateSessionFactory", sessionFactory);
		System.out.println("Hibernate SessionFactory Configured successfully");

		// Initializing Worker Thread Pool
		ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
		servletContextEvent.getServletContext().setAttribute("executor", executor);
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// close Hibernate Session
		SessionFactory sessionFactory = (SessionFactory) servletContextEvent.getServletContext().getAttribute("HibernateSessionFactory");
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			sessionFactory.close();
		}

		// shutdown Worker Thread Pool
		ThreadPoolExecutor executor = (ThreadPoolExecutor) servletContextEvent.getServletContext().getAttribute("executor");
		executor.shutdown();

	}

}
