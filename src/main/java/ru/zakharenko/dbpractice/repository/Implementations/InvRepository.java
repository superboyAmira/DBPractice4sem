package ru.zakharenko.dbpractice.repository.Implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.repository.BaseRepository;
import ru.zakharenko.dbpractice.repository.IInvRepository;

@Repository
public class InvRepository extends BaseRepository<Investor> implements IInvRepository {
	public InvRepository() {
		super(Investor.class);
	}
}
