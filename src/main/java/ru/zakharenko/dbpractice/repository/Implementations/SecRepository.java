package ru.zakharenko.dbpractice.repository.Implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.zakharenko.dbpractice.domain.Security;
import ru.zakharenko.dbpractice.repository.BaseRepository;
import ru.zakharenko.dbpractice.repository.ISecRepository;

import java.util.List;

@Repository
public class SecRepository extends BaseRepository<Security> implements ISecRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public SecRepository() {
		super(Security.class);
	}

	@Override
	@Transactional
	public List<Security> getAllWithBounds(Double lowerBound, Double upperBound) {
		lowerBound = (lowerBound < 0) ? 0 : lowerBound;
		upperBound = (upperBound < 0) ? Double.MAX_VALUE : lowerBound;
		return entityManager.createQuery("SELECT s FROM Security s WHERE s.currentPrice BETWEEN :lowerBound AND :upperBound", Security.class)
				.setParameter("lowerBound", lowerBound)
				.setParameter("upperBound", upperBound)
				.getResultList();
	}
}
