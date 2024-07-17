package ru.zakharenko.dbpractice.domain;

import jakarta.persistence.*;
import java.util.UUID;

/* To-do
 * Алгоритм для подсчета объема торгов по бумаге.
 */

@Entity
@Table(name = "security")
public class Security extends BaseDomain {
	private String name;
	private SecurityType type;
	private String ticker;
	private Integer currentPrice;

	public Security() {
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
//	public void setAvgVolume(Integer avgVolume) {
//		this.avgVolume = avgVolume;
//	}
}
