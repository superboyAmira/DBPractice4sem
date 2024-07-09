package ru.zakharenko.dbpractice.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/* To-do
 * Обработка закрытия позиции при транзакции связанной с ней
 */

@Entity
@Table(name = "position")
public class Position {
	private UUID id;
	private Portfolio portfolio;
	private Security security;
	private Integer amount;
	private Double priceBuy;
	private Timestamp posDate;

	@Id
	@GeneratedValue(generator = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", unique = true, nullable = false)
	public UUID getId() {
		return id;
	}

	@OneToOne
	@Column(name = "portfolio_id", nullable = false)
	public Portfolio getPortfolio() {
		return portfolio;
	}

	@OneToOne
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

	public void setId(UUID id) {
		this.id = id;
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
