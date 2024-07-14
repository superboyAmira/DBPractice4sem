package ru.zakharenko.dbpractice.service;

import org.springframework.stereotype.Service;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.domain.Portfolio;
import ru.zakharenko.dbpractice.domain.Position;
import ru.zakharenko.dbpractice.domainService.PortfolioSpecialService;
import ru.zakharenko.dbpractice.repository.InvestorRepository;
import ru.zakharenko.dbpractice.repository.PortfolioRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PortfolioService implements IBaseService<Portfolio> {
	private PortfolioRepository repository;
	public PortfolioService(PortfolioRepository repository) { this.repository = repository; }
	public Portfolio createEntity(Portfolio entity) {
		return repository.create(entity);
	}
	public List<Portfolio> getAll() {
		return repository.getAll();
	}
	public Portfolio getByUUID(UUID id) {
		return repository.getByUUID(id);
	}
	public Portfolio update(Portfolio entity) {
		return repository.update(entity);
	}
}
