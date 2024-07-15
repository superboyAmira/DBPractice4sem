package ru.zakharenko.dbpractice.DTO;

import ru.zakharenko.dbpractice.domain.Security;

import java.util.Map;

public class LiquidityReport {
	public Map<Security, String> reportForEach;

	public LiquidityReport(Map<Security, String> reportForEach) {
		this.reportForEach = reportForEach;
	}
}
