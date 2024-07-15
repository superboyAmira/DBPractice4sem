package ru.zakharenko.dbpractice.domainService;

import org.springframework.stereotype.Service;
import ru.zakharenko.dbpractice.domain.Portfolio;
import ru.zakharenko.dbpractice.domain.Position;
import ru.zakharenko.dbpractice.domain.Security;
import ru.zakharenko.dbpractice.repository.PortfolioRepository;
import ru.zakharenko.dbpractice.repository.SecurityRepository;
import ru.zakharenko.dbpractice.DTO.PortfolioRecommendations;

import java.util.List;

@Service
public class ScenarioTwoService {
	private final PortfolioRepository portfolioRepository;
	private final SecurityRepository securityRepository;

	public ScenarioTwoService(PortfolioRepository portfolioRepository, SecurityRepository securityRepository) {
		this.portfolioRepository = portfolioRepository;
		this.securityRepository = securityRepository;
	}

	public PortfolioRecommendations diversifyAdvices(Portfolio entity) {
		List<Position> openedPositions = portfolioRepository.getAllPositionLinkedPortfolio(entity); // все открытые позиции в портфеле
		Double allSum = entity.getFiatMoney();
		for (Position pos : openedPositions) {
			allSum += pos.getSecurity().getCurrentPrice();
		}
		return getRecommendations(allSum, openedPositions.size());
	}
	private PortfolioRecommendations getRecommendations(Double sum, Integer countOfPositions) {
		if (sum >= 10000) {
			if (countOfPositions < 3) {
				List<Security> ret = securityRepository.getAllWithBounds(0.0, 500.0);
				return new PortfolioRecommendations("Your portfolio is not diversified" +
						"You should consider the following securities:\n", ret);
			} else if (countOfPositions < 5) {
				List<Security> ret = securityRepository.getAllWithBounds(0.0, 200.0);
				return new PortfolioRecommendations("Your portfolio is diversified," +
						" but you can add the following securities", ret);
			} else {
				return new PortfolioRecommendations("Your portfolio is diversified," +
						" great Job!", null);
			}
		} else if (sum >= 50000) {
			if (countOfPositions < 5) {
				List<Security> ret = securityRepository.getAllWithBounds(0.0, 1500.0);
				return new PortfolioRecommendations("Your portfolio is not diversified" +
						"You should consider the following securities:\n", ret);
			} else if (countOfPositions < 10) {
				List<Security> ret = securityRepository.getAllWithBounds(0.0, 1000.0);
				return new PortfolioRecommendations("Your portfolio is diversified," +
						" but you can add the following securities", ret);
			} else {
				return new PortfolioRecommendations("Your portfolio is diversified," +
						" great Job!", null);
			}
		}  else if (sum >= 100000) {
			if (countOfPositions < 10) {
				List<Security> ret = securityRepository.getAllWithBounds(0.0, 3000.0);
				return new PortfolioRecommendations("Your portfolio is not diversified" +
						"You should consider the following securities:\n", ret);
			} else if (countOfPositions < 20) {
				List<Security> ret = securityRepository.getAllWithBounds(0.0, 1500.0);
				return new PortfolioRecommendations("Your portfolio is diversified," +
						" but you can add the following securities", ret);
			} else {
				return new PortfolioRecommendations("Your portfolio is diversified," +
						" great Job!", null);
			}
		} else {
			if (countOfPositions < 10) {
				List<Security> ret = securityRepository.getAllWithBounds(100.0, -1.0);
				return new PortfolioRecommendations("Your portfolio is not diversified" +
						"You should consider the following securities:\n", ret);
			} else if (countOfPositions < 20) {
				List<Security> ret = securityRepository.getAllWithBounds(0.0, -1.0);
				return new PortfolioRecommendations("Your portfolio is diversified," +
						" but you can add the following securities", ret);
			} else {
				return new PortfolioRecommendations("Your portfolio is diversified," +
						" great Job!", null);
			}
		}
	}
}
