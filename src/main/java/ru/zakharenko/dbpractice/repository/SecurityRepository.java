package ru.zakharenko.dbpractice.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.zakharenko.dbpractice.domain.Security;
import ru.zakharenko.dbpractice.repository.util.HibernateUtil;

import java.util.List;

@Repository
public class SecurityRepository extends BaseRepositry<Security> {
	public SecurityRepository() {
		super(Security.class);
	}

	public List<Security> getAllWithBounds(Double lowerBound, Double upperBound) {
		lowerBound = (lowerBound < 0) ? 0 : lowerBound;
		upperBound = (upperBound < 0) ? Double.MAX_VALUE : lowerBound;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Security where currentPrice > :lower and currentPrice < :upper", Security.class)
					.setParameter("lower", lowerBound)
					.setParameter("upper", upperBound)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
