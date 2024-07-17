package ru.zakharenko.dbpractice.domainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zakharenko.dbpractice.DTO.ProfitStatistic;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.domain.Portfolio;
import ru.zakharenko.dbpractice.domain.Position;
import ru.zakharenko.dbpractice.repository.Implementations.PortRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ScenarioOneService {
	@Autowired
	PortRepository portfolioRepository;

	public ProfitStatistic getProfitAllPortfolio(Investor entity) {
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

	public ProfitStatistic getProfitSinglePortfolio(Portfolio entity) {
		Map<UUID, Double> profitAll = new HashMap<>();
		List<Position> positions = portfolioRepository.getAllPositionLinkedPortfolio(entity);
		for (Position pos : positions) {
			profitAll.merge(pos.getId(), pos.getPriceBuy() - pos.getSecurity().getCurrentPrice(), Double::sum);
		}
		return new ProfitStatistic(profitAll, profitAll.values().stream().mapToDouble(Double::doubleValue).sum());
	}
}