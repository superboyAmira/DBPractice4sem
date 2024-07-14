package ru.zakharenko.dbpractice.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.repository.domainRepository.InvestorSpecialRepository;
import ru.zakharenko.dbpractice.repository.util.HibernateUtil;

import java.util.List;
import java.util.UUID;

@Repository
public class InvestorRepository extends BaseRepositry<Investor> implements InvestorSpecialRepository {
	public InvestorRepository() {
		super(Investor.class); // явно вызываем конструктор в абстрактном дженерике нашем
	}
	public List<Investor> getprofitByUUIDInvestor(UUID investorID) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Investor";
			return session.createQuery(hql, Investor.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

