package ru.zakharenko.dbpractice.domain;

import javax.persistence.*;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "investor")
public class Investor {
	private UUID id;
	private String name;
	private String lastName;
	private String birthDate;
	private String email;

	@Id
	@GeneratedValue(generator = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", unique = true, nullable = false)
	UUID getId() { return this.id; }

	@Column(name = "name", nullable = false, length = 15)
	public String getName() {
		return name;
	}

	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}

	@Column(name = "birth_date", nullable = false)
	public String getBirthDate() {
		return birthDate;
	}

	@Column(name = "email", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	void setId(UUID id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public void setEmail(String email) {
		String regexEmail = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattEmail = Pattern.compile(regexEmail);
		Matcher matcherEmail = pattEmail.matcher(email);
		if (matcherEmail.matches()) {
			this.email = email;
		}
	}
}
