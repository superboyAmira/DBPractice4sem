package ru.zakharenko.dbpractice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transaction")
public class TransactionSecurity extends BaseDomain {
	private Investor seller;
	private Investor buyer;
	private Security security;
	private TransactionType type;
	private Timestamp trTime;

	public TransactionSecurity(Investor seller,
	                           Investor buyer,
	                           Security security, TransactionType type, Timestamp trTime) {
		this.setStatus(true);
		this.seller = seller;
		this.buyer = buyer;
		this.security = security;
		this.type = type;
		this.trTime = trTime;
	}

	public TransactionSecurity() {
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id", referencedColumnName = "id", nullable = true)
	@JsonBackReference
	public Investor getSeller() {
		return seller;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "buyer_id", referencedColumnName = "id", nullable = true)
	@JsonBackReference
	public Investor getBuyer() {
		return buyer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "security_id", referencedColumnName = "id", nullable = true)
	@JsonBackReference
	public Security getSecurity() {
		return security;
	}

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	public TransactionType getType() {
		return type;
	}

	@Column(name = "date", nullable = false)
	public Timestamp getTrTime() { return trTime; }

	public void setSeller(Investor seller) {
		this.seller = seller;
	}

	public void setBuyer(Investor buyer) {
		this.buyer = buyer;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public void setTrTime(Timestamp dateTime) { this.trTime = dateTime; }
}
