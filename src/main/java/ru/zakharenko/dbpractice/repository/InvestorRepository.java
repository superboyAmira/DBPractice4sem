package ru.zakharenko.dbpractice.repository;

import org.springframework.stereotype.Repository;
import ru.zakharenko.dbpractice.domain.Investor;

@Repository
public class InvestorRepository extends BaseRepositry<Investor> {
	public InvestorRepository() {
		super(Investor.class); // явно вызываем конструктор в абстрактном дженерике нашем
	}
}

