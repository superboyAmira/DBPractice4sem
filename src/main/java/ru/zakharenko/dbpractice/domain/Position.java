package ru.zakharenko.dbpractice.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/* To-do
 * Обработка закрытия позиции при транзакции связанной с ней
 * Обработка удаления бумаги (позиция заблокирована, но не удалена!)
 */

@Entity
@Table(name = "position")
public class Position extends BaseDomain {
	private Portfolio portfolio;
	private Security security;
	private Integer amount;
	private Double priceBuy;
	private Timestamp posDate;

	@OneToOne(mappedBy = "portfolio", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Column(name = "portfolio_id", nullable = false)
	public Portfolio getPortfolio() {
		return portfolio;
	}

	@OneToOne(mappedBy = "portfolio", cascade = CascadeType.REMOVE, orphanRemoval = false)
	@Column(name = "security_id", nullable = false)
	public Security getSecurity() {
		return security;
	}

	@Column(name = "amount", nullable = false)
	public Integer getAmount() {
		return amount;
	}

	@Column(name = "price_buy", nullable = false)
	public Double getPriceBuy() {
		return priceBuy;
	}

	@Column(name = "pos_date", nullable = false)
	public Timestamp getPosDate() {
		return posDate;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setPriceBuy(Double priceBuy) {
		this.priceBuy = priceBuy;
	}

	public void setPosDate(Timestamp posDate) {
		this.posDate = posDate;
	}
}
