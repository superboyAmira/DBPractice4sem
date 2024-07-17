package ru.zakharenko.dbpractice.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "security")
public class Security extends BaseDomain {

	private String name;
	private SecurityType type;
	private String ticker;
	private Integer currentPrice;
	private Set<TransactionSecurity> transactions;
	private Set<Position> positions;
	public Security() {
	}

	@OneToMany(mappedBy = "security", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	public Set<Position> getPositions() {
		return positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

	public Security(String name, SecurityType type, String ticker, Integer currentPrice) {
		this.setStatus(true);
		this.name = name;
		this.type = type;
		this.ticker = ticker;
		this.currentPrice = currentPrice;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	public SecurityType getType() {
		return type;
	}

	@Column(name = "ticker", nullable = false, unique = true)
	public String getTicker() {
		return ticker;
	}

	@Column(name = "current_price", nullable = false)
	public Integer getCurrentPrice() {
		return currentPrice;
	}

	@OneToMany(mappedBy = "security", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	public Set<TransactionSecurity> getTransactions() {
		return transactions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(SecurityType type) {
		this.type = type;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public void setCurrentPrice(Integer currentPrice) {
		this.currentPrice = currentPrice;
	}

	public void setTransactions(Set<TransactionSecurity> transactions) {
		this.transactions = transactions;
	}
}
