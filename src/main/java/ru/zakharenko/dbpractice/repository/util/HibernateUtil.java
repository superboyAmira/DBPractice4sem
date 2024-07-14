package ru.zakharenko.dbpractice.repository.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.zakharenko.dbpractice.domain.TransactionSecurity;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			configuration.addAnnotatedClass(ru.zakharenko.dbpractice.domain.Investor.class);
			configuration.addAnnotatedClass(ru.zakharenko.dbpractice.domain.Portfolio.class);
			configuration.addAnnotatedClass(ru.zakharenko.dbpractice.domain.Position.class);
			configuration.addAnnotatedClass(ru.zakharenko.dbpractice.domain.Security.class);
			configuration.addAnnotatedClass(TransactionSecurity.class);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

	public static SessionFactory getSessionFactory() {
		return getSessionFactory();
	}
}