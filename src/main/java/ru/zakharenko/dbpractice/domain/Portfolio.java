package ru.zakharenko.dbpractice.domain;

import javax.persistence.*;
import java.util.UUID;

/* To-do
 * Ограничение на уменьшение количества средств при "выводе" в портфеле для типа ИИС.
 */

@Entity
@Table(name = "portfolio")
public class Portfolio extends BaseDomain {
	private Investor investorId;
	private String name;
	private PortfolioType type;

	private Double fiatMoney;
//	private Double profit;

	public Portfolio(UUID id, Boolean status, Investor investorId, String name, PortfolioType type, Double fiatMoney) {
		super(id, status);
		this.investorId = investorId;
		this.name = name;
		this.type = type;
		this.fiatMoney = fiatMoney;
	}
	@OneToOne(mappedBy = "investor", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Column(name = "investor_id", nullable = false)
	public Investor getInvestor() {
		return this.investorId;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	@Column(name = "type", nullable = false)
	public PortfolioType getType() {
		return type;
	}

	@Column(name = "fiat_money", nullable = false)
	public Double getFiatMoney() {
		return fiatMoney;
	}

//	@Column(name = "profit", nullable = false)
//	public Double getProfit() {
//		return profit;
//	}

	public void setInvestor(Investor investor) {
		this.investorId = investor;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(PortfolioType type) {
		this.type = type;
	}

	public void setFiatMoney(Double generalPortfolioMoney) {
		this.fiatMoney = generalPortfolioMoney;
	}

//	public void setProfit(Double profit) {
//		this.profit = profit;
//	}

}
