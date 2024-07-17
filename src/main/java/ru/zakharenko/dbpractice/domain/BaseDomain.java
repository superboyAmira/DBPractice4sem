package ru.zakharenko.dbpractice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseDomain {
	private UUID id;
	private boolean status;

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
