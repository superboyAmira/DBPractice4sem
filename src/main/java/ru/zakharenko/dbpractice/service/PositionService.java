package ru.zakharenko.dbpractice.service;

import ru.zakharenko.dbpractice.domain.Position;
import ru.zakharenko.dbpractice.repository.PositionRepository;

import java.util.List;
import java.util.UUID;

public class PositionService implements IBaseService<Position> {
	private PositionRepository positionRepository;

	public PositionService(PositionRepository positionRepository) { this.positionRepository = positionRepository; }
	@Override
	public List<Position> getAll() {
		return positionRepository.getAll();
	}

	@Override
	public Position update(Position position) {
		return positionRepository.update(position);
	}

	@Override
	public Position getByUUID(UUID id) {
		return positionRepository.getByUUID(id);
	}

	@Override
	public Position createEntity(Position position) {
		return positionRepository.create(position);
	}
}
