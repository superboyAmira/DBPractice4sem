package ru.zakharenko.dbpractice.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.UUID;

/* To-do
 * Ограничение на уменьшение количества средств при "выводе" в портфеле для типа ИИС.
 */

@Entity
@Table(name = "portfolio")
public class Portfolio {
	private UUID id;
	private Investor investor_id;
	private String name;
	private PortfolioType type;
	private Integer generalPortfolioMoney;
	private Double profit;

	@Id
	@GeneratedValue(generator = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", unique = true, nullable = false)
	public UUID getId() {
		return id;
	}

	@OneToOne(mappedBy = "investor", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Column(name = "investor_id", nullable = false)
	public Investor getInvestor() {
		return this.investor_id;
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

	public void setId(UUID id) {
		this.id = id;
	}

	public void setInvestor(Investor investor) {
		this.investor_id = investor;
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
