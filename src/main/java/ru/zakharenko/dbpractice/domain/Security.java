package ru.zakharenko.dbpractice.domain;

import javax.persistence.*;
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
	private boolean status;
//	private Integer avgVolume; не должна быть подсчитана сразу в бд, нужно вынести в домейн сервис

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	@Column(name = "type", nullable = false)
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

//	@Column(name = "avg_volume", nullable = false)
//	public Integer getAvgVolume() {
//		return avgVolume;
//	}
	@Column(name = "status", nullable = false)
	public boolean getStatus() { return this.status; }

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

	public void setStatus(boolean status) { this.status = status; }
//	public void setAvgVolume(Integer avgVolume) {
//		this.avgVolume = avgVolume;
//	}
}
