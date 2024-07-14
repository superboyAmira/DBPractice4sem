package ru.zakharenko.dbpractice.specailsJSON;

import ru.zakharenko.dbpractice.domain.Security;

import java.util.List;

public class PortfolioRecommendations {
	private String text;
	private List<Security> securityListToRecommended;
	public PortfolioRecommendations(String text, List<Security> securityList) {
		this.text = text;
		securityListToRecommended = securityList;
	}
}
