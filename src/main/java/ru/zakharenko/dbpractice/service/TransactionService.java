package ru.zakharenko.dbpractice.service;

import ru.zakharenko.dbpractice.domain.TransactionSecurity;
import ru.zakharenko.dbpractice.repository.TransactionSecurityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService implements IBaseService<TransactionSecurity> {
	private TransactionSecurityRepository transactionSecurityRepository;

	public TransactionService(TransactionSecurityRepository repository) {
		this.transactionSecurityRepository = repository;
	}

	@Override
	public TransactionSecurity createEntity(TransactionSecurity transactionSecurity) {
		return transactionSecurityRepository.create(transactionSecurity);
	}

	@Override
	public TransactionSecurity getByUUID(UUID id) {
		return transactionSecurityRepository.getByUUID(id);
	}

	@Override
	public TransactionSecurity update(TransactionSecurity transactionSecurity) {
		return transactionSecurityRepository.update(transactionSecurity);
	}

	@Override
	public List<TransactionSecurity> getAll() {
		List<TransactionSecurity> transactionSecurities = transactionSecurityRepository.getAll();
		List<TransactionSecurity> finalList = new ArrayList<>();
		for (TransactionSecurity transactionSecurity : transactionSecurities) {
			if (transactionSecurity.getStatus()) {
				finalList.add(transactionSecurity);
			}
		}
		return finalList;
	}

	@Override
	public TransactionSecurity changeVisible(TransactionSecurity transactionSecurity) {
		transactionSecurity.setStatus((transactionSecurity.getStatus()) ? false : true);
		return transactionSecurityRepository.changeVisible(transactionSecurity);
	}
}
