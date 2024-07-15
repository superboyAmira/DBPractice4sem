package ru.zakharenko.dbpractice.domainService;

import ru.zakharenko.dbpractice.DTO.ProfitStatistic;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.domain.Portfolio;
import ru.zakharenko.dbpractice.domain.Position;
import ru.zakharenko.dbpractice.repository.PortfolioRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ScenarioOneService {
	PortfolioRepository portfolioRepository;

	public ProfitStatistic scenarioOneAllPortfolio(Investor entity) {
		Map<UUID, Double> profitAll = new HashMap<>();
		List<Portfolio> portfolio = portfolioRepository.getByInvestorId(entity);
		for (Portfolio por : portfolio) {
			List<Position> positions = portfolioRepository.getAllPositionLinkedPortfolio(por);
			for (Position pos : positions) {
				profitAll.merge(pos.getId(), pos.getPriceBuy() - pos.getSecurity().getCurrentPrice(), Double::sum);
			}
		}
		return new ProfitStatistic(profitAll, profitAll.values().stream().mapToDouble(Double::doubleValue).sum());
	}

	public ProfitStatistic scenarioOneSinglePortfolio(Portfolio entity) {
		Map<UUID, Double> profitAll = new HashMap<>();
		List<Position> positions = portfolioRepository.getAllPositionLinkedPortfolio(entity);
		for (Position pos : positions) {
			profitAll.merge(pos.getId(), pos.getPriceBuy() - pos.getSecurity().getCurrentPrice(), Double::sum);
		}
		return new ProfitStatistic(profitAll, profitAll.values().stream().mapToDouble(Double::doubleValue).sum());
	}
}