package ru.zakharenko.dbpractice.repository.Implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.zakharenko.dbpractice.domain.TransactionSecurity;
import ru.zakharenko.dbpractice.repository.BaseRepository;
import ru.zakharenko.dbpractice.repository.ITranRepository;

import java.util.*;

@Repository
public class TranRepository extends BaseRepository<TransactionSecurity> implements ITranRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public TranRepository() {
		super(TransactionSecurity.class);
	}

	@Override
	public List<TransactionSecurity> getAllTransactionsOnSecurityThisMonth(UUID securityId) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		return entityManager.createQuery("from TransactionSecurity where security.id = :secId and MONTH(trTime) = :currentMonth", TransactionSecurity.class)
				.setParameter("secId", securityId)
				.setParameter("currentMonth", calendar.get(Calendar.MONTH) + 1)
				.getResultList();
	}

	@Override
	@Transactional
	public List<TransactionSecurity> findBySellerIdOrBuyerIdAndTrTimeYear(UUID investorId) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		return entityManager.createQuery("from TransactionSecurity where (seller.id = :inv or buyer.id = :inv) and YEAR(trTime) = :currentYEAR", TransactionSecurity.class)
				.setParameter("inv", investorId)
				.setParameter("currentYEAR", calendar.get(Calendar.YEAR))
				.getResultList();
	}
}
