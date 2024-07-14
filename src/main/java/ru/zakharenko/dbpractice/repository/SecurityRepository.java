package ru.zakharenko.dbpractice.repository;

import ru.zakharenko.dbpractice.domain.Security;
import ru.zakharenko.dbpractice.repository.domainRepository.InvestorSpecialRepository;

public class SecurityRepository extends BaseRepositry<Security> {
	public SecurityRepository() {
		super(Security.class);
	}
}
