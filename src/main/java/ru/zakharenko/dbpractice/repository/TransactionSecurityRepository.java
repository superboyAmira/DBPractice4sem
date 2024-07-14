package ru.zakharenko.dbpractice.repository;

import org.hibernate.Session;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.domain.TransactionSecurity;
import ru.zakharenko.dbpractice.repository.util.HibernateUtil;

import java.util.List;
import java.util.UUID;

public class TransactionSecurityRepository extends BaseRepositry<TransactionSecurity> {
	public TransactionSecurityRepository() {
		super(TransactionSecurity.class);
	}


}
