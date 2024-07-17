package ru.zakharenko.dbpractice.domain;

public enum SecurityType {
	Stock(0),
	Bond(1),
	Future(2);

	private final int index;

	SecurityType(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
