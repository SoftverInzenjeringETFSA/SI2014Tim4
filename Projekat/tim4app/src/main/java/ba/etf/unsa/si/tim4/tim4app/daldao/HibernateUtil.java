package ba.etf.unsa.si.tim4.tim4app.daldao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			Logger l = Logger.getAnonymousLogger();
			l.log(Level.SEVERE, ex.getMessage(), ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}