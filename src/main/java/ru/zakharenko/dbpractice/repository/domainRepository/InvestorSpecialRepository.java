package ru.zakharenko.dbpractice.repository.domainRepository;

import org.hibernate.Session;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.repository.util.HibernateUtil;

import java.util.List;
import java.util.UUID;

public interface InvestorSpecialRepository {
	public List<Investor> getprofitByUUIDInvestor(UUID investorID);

}
