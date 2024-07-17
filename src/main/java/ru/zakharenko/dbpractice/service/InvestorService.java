package ru.zakharenko.dbpractice.service;

import org.springframework.stereotype.Service;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.repository.Implementations.InvRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InvestorService implements IBaseService<Investor> {
	private InvRepository repository;
	public InvestorService(InvRepository repository) { this.repository = repository; }
	public Investor createEntity(Investor entity) {
		return repository.create(entity);
	}
	public List<Investor> getAll() {
		List<Investor> investorList = repository.getAll();
		List<Investor> finalList = new ArrayList<>();
		for (Investor inv : investorList) {
			if (inv.getStatus()) {
				finalList.add(inv);
			}
		}
		return finalList;
	}
	public Investor getByUUID(UUID id) {
		return repository.getByUUID(id);
	}
	public Investor update(Investor entity) {
		return repository.update(entity);
	}
	public Investor changeVisible(Investor entity) {
		entity.setStatus((entity.getStatus()) ? false : true);
		return repository.changeVisible(entity);
	}
}
