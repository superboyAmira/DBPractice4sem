package ru.zakharenko.dbpractice.service;

import java.util.List;
import java.util.UUID;

public interface IBaseService<DomainEntity> {
	DomainEntity createEntity(DomainEntity entity);
	List<DomainEntity> getAll();
	DomainEntity getByUUID(UUID id);
	DomainEntity update(DomainEntity entity);
	DomainEntity changeVisible(DomainEntity entity);
}
