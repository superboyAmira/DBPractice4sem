package ru.zakharenko.dbpractice.DTO;

import ru.zakharenko.dbpractice.domain.Security;

import java.util.Map;
import java.util.UUID;

public class ProfitStatistic {
	public Map<UUID, Double> profitAll;
	public ProfitStatistic(Map<UUID, Double> profitForEachSecurity) {
		this.profitAll = profitForEachSecurity;
	}
}
