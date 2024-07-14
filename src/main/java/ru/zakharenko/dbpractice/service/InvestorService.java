package ru.zakharenko.dbpractice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.repository.InvestorRepository;

import java.util.List;
import java.util.UUID;

@Service
public class InvestorService implements IBaseService<Investor> {
	private InvestorRepository repository;
	public InvestorService(InvestorRepository repository) { this.repository = repository; }
	public Investor createEntity(Investor entity) {
		return repository.create(entity);
	}

	public List<Investor> getAll() {
		return repository.getAll();
	}
	public Investor getByUUID(UUID id) {
		return repository.getByUUID(id);
	}
	public Investor update(Investor entity) {
		return repository.update(entity);
	}
}
