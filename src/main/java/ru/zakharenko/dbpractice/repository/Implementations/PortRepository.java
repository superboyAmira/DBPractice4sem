package ru.zakharenko.dbpractice.repository.Implementations;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.domain.Portfolio;
import ru.zakharenko.dbpractice.domain.Position;
import ru.zakharenko.dbpractice.repository.BaseRepository;
import ru.zakharenko.dbpractice.repository.IPortRepository;

import java.util.List;

@Repository
public class PortRepository extends BaseRepository<Portfolio> implements IPortRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public PortRepository() {
		super(Portfolio.class);
	}

	@Override
	@Transactional
	public List<Position> getAllPositionLinkedPortfolio(Portfolio entity) {
		return entityManager.createQuery("from Position where portfolio = :portfolioId", Position.class)
				.setParameter("portfolioId", entity)
				.getResultList();
	}

	@Override
	@Transactional
	public List<Portfolio> getByInvestorId(Investor investor) {
		return entityManager.createQuery("from Portfolio where investor.id = :investorId", Portfolio.class)
				.setParameter("investorId", investor.getId())
				.getResultList();
	}
}
