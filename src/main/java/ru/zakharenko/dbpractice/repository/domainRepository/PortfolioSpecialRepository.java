package ru.zakharenko.dbpractice.repository.domainRepository;

import java.util.UUID;

//@NoRepositoryBean
public interface PortfolioSpecialRepository {
	public boolean isDiversification(UUID PorfolioID);
}
