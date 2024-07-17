package ru.zakharenko.dbpractice.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "investor")
public class Investor extends BaseDomain {

	private String name;
	private String lastName;
	private String birthDate;
	private String email;
	private Set<Portfolio> portfolios;
	private Set<TransactionSecurity> securitiesSell;
	private Set<TransactionSecurity> securitiesBuy;

	public Investor() {
	}

	public Investor(String name, String lastName, String birthDate, String email) {
		this.name = name;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.setStatus(true);
		setEmail(email);
	}

	@Column(name = "name", nullable = false, length = 15)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "birth_date", nullable = false)
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "email", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		String regexEmail = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattEmail = Pattern.compile(regexEmail);
		Matcher matcherEmail = pattEmail.matcher(email);
		if (matcherEmail.matches()) {
			this.email = email;
		}
	}

	@OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	public Set<Portfolio> getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(Set<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}
	@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	public Set<TransactionSecurity> getSecuritiesBuy() {
		return securitiesBuy;
	}
	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	public Set<TransactionSecurity> getSecuritiesSell() {
		return securitiesSell;
	}

	public void setSecuritiesBuy(Set<TransactionSecurity> securitiesBuy) {
		this.securitiesBuy = securitiesBuy;
	}

	public void setSecuritiesSell(Set<TransactionSecurity> securitiesSell) {
		this.securitiesSell = securitiesSell;
	}
}
