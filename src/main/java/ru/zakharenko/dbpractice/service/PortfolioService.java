package ru.zakharenko.dbpractice.service;

import org.springframework.stereotype.Service;
import ru.zakharenko.dbpractice.domain.Portfolio;
import ru.zakharenko.dbpractice.repository.Implementations.PortRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PortfolioService implements IBaseService<Portfolio> {
	private PortRepository repository;
	public PortfolioService(PortRepository repository) { this.repository = repository; }
	public Portfolio createEntity(Portfolio entity) {
		return repository.create(entity);
	}
	public List<Portfolio> getAll() {
		List<Portfolio> portfolioList = repository.getAll();
		List<Portfolio> finalList = new ArrayList<>();
		for (Portfolio por : portfolioList) {
			if (por.getStatus()) {
				finalList.add(por);
			}
		}
		return finalList;
	}
	public Portfolio getByUUID(UUID id) {
		return repository.getByUUID(id);
	}
	public Portfolio update(Portfolio entity) {
		return repository.update(entity);
	}

	public Portfolio changeVisible(Portfolio entity) {
		entity.setStatus((entity.getStatus()) ? false : true);
		return repository.changeVisible(entity);
	}
}
