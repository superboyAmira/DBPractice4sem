package ru.zakharenko.dbpractice.DTO;

import ru.zakharenko.dbpractice.domain.Security;

import java.util.Map;
import java.util.UUID;

public class ProfitStatistic {
	public String duration;
	public Security security;
	public Double profit;

	public Map<UUID, Double> profitAll;

	public ProfitStatistic(String duration, Security security, Double profit) {
		this.duration = duration;
		this.security = security;
		this.profit = profit;
	}
	public ProfitStatistic(String duration, Map<UUID, Double> profitForEachSecurity) {
		this.duration = duration;
		this.profitAll = profitForEachSecurity;
	}
}
