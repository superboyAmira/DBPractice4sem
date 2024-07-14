package ru.zakharenko.dbpractice.repository;

import ru.zakharenko.dbpractice.domain.Portfolio;
import ru.zakharenko.dbpractice.repository.domainRepository.PortfolioSpecialRepository;

import java.util.UUID;

public class PortfolioRepository extends BaseRepositry<Portfolio> implements PortfolioSpecialRepository {
	public PortfolioRepository() {
		super(Portfolio.class);
	}

	public boolean isDiversification(UUID PorfolioID) {
		List<>
		return
	}
}
