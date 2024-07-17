package ru.zakharenko.dbpractice.domainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zakharenko.dbpractice.DTO.LiquidityReport;
import ru.zakharenko.dbpractice.domain.Portfolio;
import ru.zakharenko.dbpractice.domain.Position;
import ru.zakharenko.dbpractice.domain.Security;
import ru.zakharenko.dbpractice.domain.TransactionSecurity;
import ru.zakharenko.dbpractice.repository.Implementations.PortRepository;
import ru.zakharenko.dbpractice.repository.Implementations.TranRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScenarioThreeService {
	@Autowired
	TranRepository transactionSecurityRepository;
	@Autowired
	PortRepository portfolioRepository;

	private String isLiquidSecurity(Security security) {
		List<TransactionSecurity> transactionSecurities = transactionSecurityRepository.getAllTransactionsOnSecurityThisMonth(security.getId());
		if (transactionSecurities.size() == 0) {
			return "Security has no data on trading volume for this month yet";
		}
		String infoSecurity = " -> ADTV = " + transactionSecurities.size();
		if (transactionSecurities.size() > 10) {
			return "Security is very liquid and volotile" + infoSecurity;
		} else if (transactionSecurities.size() > 5) {
			return "Security is in average demand" + infoSecurity;
		} else {
			return "Security is not in strong demand, it will be difficult for you to find a buyer" + infoSecurity;
		}
	}

	public LiquidityReport getLiquidityReport(Portfolio portfolio) {
		List<Position> allPos = portfolioRepository.getAllPositionLinkedPortfolio(portfolio);
		Map<Security, String> res = new HashMap<>();
		for (Position pos : allPos) {
			res.put(pos.getSecurity(), this.isLiquidSecurity(pos.getSecurity()));
		}
		return new LiquidityReport(res);
	}
}
