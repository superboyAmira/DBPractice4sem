package ru.zakharenko.dbpractice.DTO;

import ru.zakharenko.dbpractice.domain.Security;

import java.util.List;

public class PortfolioRecommendations {
	public String text;
	public List<Security> securityListToRecommended;
	public PortfolioRecommendations(String text, List<Security> securityList) {
		this.text = text;
		securityListToRecommended = securityList;
	}
}
