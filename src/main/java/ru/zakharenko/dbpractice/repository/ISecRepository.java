package ru.zakharenko.dbpractice.repository;

import ru.zakharenko.dbpractice.domain.Security;

import java.util.List;

public interface ISecRepository {
	public List<Security> getAllWithBounds(Double lowerBound, Double upperBound);
}
