package ru.zakharenko.dbpractice.domain;

public enum PortfolioType {
	brokerageAccount(0),
	IIS(1);

	private final int index;

	PortfolioType(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
