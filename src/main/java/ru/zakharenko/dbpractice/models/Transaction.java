package ru.zakharenko.dbpractice.models;

import javax.persistence.*;
import java.util.UUID;

/* To-do
* Обработать создание зеркальных транзакций (SELL-BUY)
* Валидация транзакции по открытой позиции продавца
* Валидация транзакции по фиатным средствам покупателя
* Кастомные конструкторы под требования алгоритмики бизнес-сценария
*/
@Entity
@Table(name = "transaction")
public class Transaction {
	private UUID id;
	private Investor seller;
	private Investor buyer;
	private Security security;
	private TransactionType type;

	@Id
	@GeneratedValue(generator = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", unique = true, nullable = false)
	public UUID getId() {
		return id;
	}

	@ManyToOne
	@Column(name = "seller_id", nullable = false)
	public Investor getSeller() {
		return seller;
	}

	@ManyToOne
	@Column(name = "buyer_id", nullable = false)
	public Investor getBuyer() {
		return buyer;
	}

	@OneToOne
	@Column(name = "security_id", nullable = false)
	public Security getSecurity() {
		return security;
	}

	@Column(name = "type", nullable = false)
	public TransactionType getType() {
		return type;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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
}
