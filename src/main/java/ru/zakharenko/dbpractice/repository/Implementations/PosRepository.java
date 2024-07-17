package ru.zakharenko.dbpractice.repository.Implementations;

import org.springframework.stereotype.Repository;
import ru.zakharenko.dbpractice.domain.Position;
import ru.zakharenko.dbpractice.repository.BaseRepository;
import ru.zakharenko.dbpractice.repository.IPosRepository;


@Repository
public class PosRepository extends BaseRepository<Position> implements IPosRepository {
	public PosRepository() {
		super(Position.class);
	}
}
