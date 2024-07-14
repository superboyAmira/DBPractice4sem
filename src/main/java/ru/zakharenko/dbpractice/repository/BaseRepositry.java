package ru.zakharenko.dbpractice.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.zakharenko.dbpractice.repository.util.HibernateUtil;

import java.util.List;
import java.util.UUID;

public abstract class BaseRepositry<DomainEntity> {
	private final Class<DomainEntity> entityClass;

	public BaseRepositry(Class<DomainEntity> entityClass) { this.entityClass  = entityClass; };
	public DomainEntity create(DomainEntity entity) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
			return entity;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}
	public List<DomainEntity> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from " + entityClass.getName(), entityClass).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public DomainEntity getByUUID(UUID id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(entityClass, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public DomainEntity update(DomainEntity entity) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();
			return entity;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}

}
