package ru.zakharenko.dbpractice.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseDomain {
	private UUID id;

	@Id
	@GeneratedValue(generator = "org.hibernate.id.UUIDGenerator")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
