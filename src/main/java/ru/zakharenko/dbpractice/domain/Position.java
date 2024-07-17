package ru.zakharenko.dbpractice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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

	public Position() {
	}
	public Position(Portfolio portfolio, Security security, Integer amount, Double priceBuy, Timestamp posDate) {
		this.setStatus(true);
		this.portfolio = portfolio;
		this.security = security;
		this.amount = amount;
		this.priceBuy = priceBuy;
		this.posDate = posDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "portfolio_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference
	public Portfolio getPortfolio() {
		return portfolio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "security_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference
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
