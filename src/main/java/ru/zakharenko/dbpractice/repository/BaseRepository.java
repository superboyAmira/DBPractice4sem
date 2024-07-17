package ru.zakharenko.dbpractice.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
public abstract class BaseRepository<DomainEntity> {

	@PersistenceContext
	protected EntityManager entityManager;

	private final Class<DomainEntity> entityClass;

	public BaseRepository(Class<DomainEntity> entityClass) {
		this.entityClass = entityClass;
	}

	@Transactional
	public DomainEntity create(DomainEntity entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Transactional
	public List<DomainEntity> getAll() {
		return entityManager.createQuery("from " + entityClass.getName(), entityClass).getResultList();
	}

	@Transactional
	public DomainEntity getByUUID(UUID id) {
		return entityManager.find(entityClass, id);
	}

	@Transactional
	public DomainEntity update(DomainEntity entity) {
		return entityManager.merge(entity);
	}

	@Transactional
	public DomainEntity changeVisible(DomainEntity entity) {
		return this.update(entity);
	}
}
