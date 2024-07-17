package ru.zakharenko.dbpractice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "portfolio")
public class Portfolio extends BaseDomain {

	private Investor investor;
	private String name;
	private PortfolioType type;
	private Double fiatMoney;
	private Set<Position> positions;

	public Portfolio() {
	}

	public Portfolio(Investor investor, String name, PortfolioType type, Double fiatMoney) {
		this.investor = investor;
		this.name = name;
		this.type = type;
		this.fiatMoney = fiatMoney;
		this.setStatus(true);
	}

	@ManyToOne
	@JoinColumn(name = "investor_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference
	public Investor getInvestor() {
		return investor;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	public PortfolioType getType() {
		return type;
	}

	@Column(name = "fiat_money", nullable = false)
	public Double getFiatMoney() {
		return fiatMoney;
	}

	@OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	public Set<Position> getPositions() {
		return positions;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(PortfolioType type) {
		this.type = type;
	}

	public void setFiatMoney(Double fiatMoney) {
		this.fiatMoney = fiatMoney;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}
}
