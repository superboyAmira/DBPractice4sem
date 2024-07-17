package ru.zakharenko.dbpractice.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseDomain {
	private UUID id;
	private boolean status;

	public BaseDomain(UUID id, boolean status) {
		this.id = id;
		this.status = status;
	}

	@Id
	@GeneratedValue(generator = "org.hibernate.id.UUIDGenerator")
	public UUID getId() {
		return id;
	}

	@Column(name = "status", nullable = false)
	public boolean getStatus() { return this.status; };

	public void setId(UUID id) {
		this.id = id;
	}

	public void setStatus(boolean status) { this.status = status; };
}
