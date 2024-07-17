package ru.zakharenko.dbpractice.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/* To-do
* Обработать создание зеркальных транзакций (SELL-BUY)
* Валидация транзакции по открытой позиции продавца
* Валидация транзакции по фиатным средствам покупателя
* Кастомные конструкторы под требования алгоритмики бизнес-сценария
* Здесь мы закинем лоигку в сервисы, так как размерность данных может быть огромной
*/
@Entity
@Table(name = "transaction")
public class TransactionSecurity extends BaseDomain {
	private Investor seller;
	private Investor buyer;
	private Security security;
	private TransactionType type;
	private Timestamp trTime;

	public TransactionSecurity(UUID id, boolean status,
	                           Investor seller,
	                           Investor buyer,
	                           Security security, TransactionType type, Timestamp trTime) {
		super(id, status);
		this.seller = seller;
		this.buyer = buyer;
		this.security = security;
		this.type = type;
		this.trTime = trTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "seller_id", nullable = true)
	public Investor getSeller() {
		return seller;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "buyer_id", nullable = true)
	public Investor getBuyer() {
		return buyer;
	}

	@OneToOne(mappedBy = "security")
	@Column(name = "security_id", nullable = true)
	public Security getSecurity() {
		return security;
	}

	@Column(name = "type", nullable = false)
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
