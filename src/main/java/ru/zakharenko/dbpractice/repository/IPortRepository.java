package ru.zakharenko.dbpractice.repository;

import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.domain.Portfolio;
import ru.zakharenko.dbpractice.domain.Position;

import java.util.List;

public interface IPortRepository {
	public List<Position> getAllPositionLinkedPortfolio(Portfolio entity);
	public List<Portfolio> getByInvestorId(Investor investor);
}
