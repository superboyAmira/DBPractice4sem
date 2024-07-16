package ru.zakharenko.dbpractice.service;

import org.springframework.stereotype.Service;
import ru.zakharenko.dbpractice.domain.Security;
import ru.zakharenko.dbpractice.repository.SecurityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SecurityService implements IBaseService<Security> {
	private SecurityRepository securityRepository;
	public SecurityService(SecurityRepository securityRepository) {
		this.securityRepository = securityRepository;
	}
	@Override
	public Security createEntity(Security security) {
		return securityRepository.create(security);
	}
	@Override
	public Security update(Security security) {
		return securityRepository.update(security);
	}
	@Override
	public Security getByUUID(UUID id) {
		return securityRepository.getByUUID(id);
	}
	@Override
	public List<Security> getAll() {
		List<Security> securityList = securityRepository.getAll();
		List<Security> finalList = new ArrayList<>();
		for (Security security : securityList) {
			if (security.getStatus()) {
				finalList.add(security);
			}
		}
		return finalList;
	}
	@Override
	public Security changeVisible(Security security) {
		security.setStatus((security.getStatus()) ? false : true);
		return securityRepository.changeVisible(security);
	}
}
