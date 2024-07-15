package ru.zakharenko.dbpractice.domainService;

import ru.zakharenko.dbpractice.DTO.ProfitStatistic;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.domain.Portfolio;
import ru.zakharenko.dbpractice.domain.Position;
import ru.zakharenko.dbpractice.repository.PortfolioRepository;
import ru.zakharenko.dbpractice.repository.PositionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InvestorSpecialService {
	PortfolioRepository portfolioRepository;
	PositionRepository positionRepository;

	public ProfitStatistic scenarioAllSecurities(Investor entity) {
		Portfolio portfolio = portfolioRepository.getByInvestorId(entity);
		List<Position> positions = portfolioRepository.getAllPositionLinkedPortfolio(portfolio);
		Map<UUID, Double> profitAll = new HashMap<>();
		for (Position pos : positions) {
			profitAll.put(pos.getId(), pos.getPriceBuy() - pos.getSecurity().getCurrentPrice());
		}
		return new ProfitStatistic(profitAll);
	}
}