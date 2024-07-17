package ru.zakharenko.dbpractice.domain;

public enum TransactionType {
	SELL(0),
	BUY(1);

	private final int index;

	TransactionType(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
