package ru.zakharenko.dbpractice.domain;

import javax.persistence.*;

/* To-do
 * Ограничение на уменьшение количества средств при "выводе" в портфеле для типа ИИС.
 */

@Entity
@Table(name = "portfolio")
public class Portfolio extends BaseDomain {
	private Investor investorId;
	private String name;
	private PortfolioType type;
	private Integer generalPortfolioMoney;
	private Double profit;

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

	@Column(name = "general_portfolio_money", nullable = false)
	public Integer getGeneralPortfolioMoney() {
		return generalPortfolioMoney;
	}

	@Column(name = "profit", nullable = false)
	public Double getProfit() {
		return profit;
	}

	public void setInvestor(Investor investor) {
		this.investorId = investor;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(PortfolioType type) {
		this.type = type;
	}

	public void setGeneralPortfolioMoney(Integer generalPortfolioMoney) {
		this.generalPortfolioMoney = generalPortfolioMoney;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}
}
