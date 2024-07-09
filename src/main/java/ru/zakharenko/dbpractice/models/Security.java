package ru.zakharenko.dbpractice.models;

import javax.persistence.*;
import java.util.UUID;

/* To-do
 * Алгоритм для подсчета объема торгов по бумаге.
 */

@Entity
@Table(name = "security")
public class Security {
	private UUID id;
	private String name;
	private StockType type;
	private String ticker;
	private Integer currentPrice;
	private Integer avgVolume;

	@Id
	@GeneratedValue(generator = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", unique = true, nullable = false)
	public UUID getId() {
		return id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	@Column(name = "type", nullable = false)
	public StockType getType() {
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

	@Column(name = "avg_volume", nullable = false)
	public Integer getAvgVolume() {
		return avgVolume;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(StockType type) {
		this.type = type;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public void setCurrentPrice(Integer currentPrice) {
		this.currentPrice = currentPrice;
	}

	public void setAvgVolume(Integer avgVolume) {
		this.avgVolume = avgVolume;
	}
}
