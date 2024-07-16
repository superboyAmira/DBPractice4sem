package ru.zakharenko.dbpractice.repository;

import org.springframework.stereotype.Repository;
import ru.zakharenko.dbpractice.domain.Position;

@Repository
public class PositionRepository extends BaseRepositry<Position> {
	public PositionRepository() {
		super(Position.class);
	}
}
