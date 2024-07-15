package ru.zakharenko.dbpractice.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.domain.Portfolio;
import ru.zakharenko.dbpractice.domain.Position;
import ru.zakharenko.dbpractice.repository.util.HibernateUtil;

import java.util.List;

@Repository
public class PortfolioRepository extends BaseRepositry<Portfolio> {
	public PortfolioRepository() {
		super(Portfolio.class);
	}

	public List<Position> getAllPositionLinkedPortfolio(Portfolio entity) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Position where portfolio.id = :portfolioId", Position.class)
					.setParameter("portfolioId", entity.getId())
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Portfolio> getByInvestorId(Investor investor) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Portfolio where investor.id = :investorId", Portfolio.class).setParameter("investorId", investor.getId()).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
