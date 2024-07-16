package ru.zakharenko.dbpractice.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.domain.TransactionSecurity;
import ru.zakharenko.dbpractice.repository.util.HibernateUtil;

import java.util.*;

@Repository
public class TransactionSecurityRepository extends BaseRepositry<TransactionSecurity> {
	public TransactionSecurityRepository() {
		super(TransactionSecurity.class);
	}

	public List<TransactionSecurity> getAllTransactionsOnSecurityThisMonth(UUID securityId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(new Date());
			return session.createQuery("from TransactionSecurity where security.id = :secId and MONTH(trTime) = :currentMonth", TransactionSecurity.class)
					.setParameter("secId", securityId)
					.setParameter("currentMonth", calendar.get(Calendar.MONTH) + 1)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<TransactionSecurity> getAllTransactionsThisYearByInvestor(Investor investor) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(new Date());
			return session.createQuery("from TransactionSecurity where (seller.id = :inv or buyer.id = :inv) and YEAR(trTime) = :currentYEAR", TransactionSecurity.class)
					.setParameter("inv", investor.getId())
					.setParameter("currentYEAR", calendar.get(Calendar.YEAR))
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
