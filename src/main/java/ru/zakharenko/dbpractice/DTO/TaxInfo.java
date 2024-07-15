package ru.zakharenko.dbpractice.DTO;

import java.sql.Timestamp;

public class TaxInfo {
	public Timestamp dataTax;
	public Double tax;

	public TaxInfo(Timestamp dataTax, Double tax) {
		this.tax = tax;
		this.dataTax = dataTax;
	}
}
