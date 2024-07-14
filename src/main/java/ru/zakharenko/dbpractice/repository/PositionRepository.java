package ru.zakharenko.dbpractice.repository;

import ru.zakharenko.dbpractice.domain.Position;

public class PositionRepository extends BaseRepositry<Position> {
	public PositionRepository() {
		super(Position.class);
	}
}
