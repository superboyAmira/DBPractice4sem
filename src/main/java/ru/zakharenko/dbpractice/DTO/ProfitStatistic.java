package ru.zakharenko.dbpractice.DTO;

import ru.zakharenko.dbpractice.domain.Security;

import java.util.Map;
import java.util.UUID;
import java.util.function.DoubleToLongFunction;

public class ProfitStatistic {
	public Map<UUID, Double> profitAll;
	public Double finalProfit;
	public ProfitStatistic(Map<UUID, Double> profitForEachSecurity, Double finalProfit) {
		this.profitAll = profitForEachSecurity;
		this.finalProfit = finalProfit;
	}
}
