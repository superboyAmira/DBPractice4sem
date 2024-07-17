package ru.zakharenko.dbpractice.repository;

import ru.zakharenko.dbpractice.domain.TransactionSecurity;

import java.util.List;
import java.util.UUID;

public interface ITranRepository {
	public List<TransactionSecurity> getAllTransactionsOnSecurityThisMonth(UUID securityId);
	List<TransactionSecurity> findBySellerIdOrBuyerIdAndTrTimeYear(UUID investorId);
}
