package ru.zakharenko.dbpractice.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "portfolio")
public class Portfolio extends BaseDomain {
	private Investor investor;
	private String name;
	private PortfolioType type;
	private Double fiatMoney;

	public Portfolio() {
	}
	public Portfolio( Investor investor, String name, PortfolioType type, Double fiatMoney) {
		this.investor = investor;
		this.name = name;
		this.type = type;
		this.fiatMoney = fiatMoney;
		this.setStatus(true);
	}

	@ManyToOne
	@JoinColumn(name = "investor_id", referencedColumnName = "id", nullable = false)
	public Investor getInvestor() {
		return investor;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	public PortfolioType getType() {
		return type;
	}

	@Column(name = "fiat_money", nullable = false)
	public Double getFiatMoney() {
		return fiatMoney;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(PortfolioType type) {
		this.type = type;
	}

	public void setFiatMoney(Double fiatMoney) {
		this.fiatMoney = fiatMoney;
	}
}
