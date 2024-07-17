package ru.zakharenko.dbpractice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zakharenko.dbpractice.domain.*;
import ru.zakharenko.dbpractice.repository.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class DataInitializationService {
	private InvestorRepository investorRepository;
	private PortfolioRepository portfolioRepository;
	private SecurityRepository securityRepository;
	private PositionRepository positionRepository;
	private TransactionSecurityRepository transactionSecurityRepository;

	@Autowired
	public DataInitializationService(InvestorRepository investorRepository,
	                                 PortfolioRepository portfolioRepository,
	                                 SecurityRepository securityRepository,
	                                 PositionRepository positionRepository,
	                                 TransactionSecurityRepository transactionSecurityRepository) {
		this.investorRepository = investorRepository;
		this.portfolioRepository = portfolioRepository;
		this.positionRepository = positionRepository;
		this.transactionSecurityRepository = transactionSecurityRepository;
	}

	public void initializeData() {
		// Добавление инвесторов
		Investor investor1 = new Investor(UUID.randomUUID(), true, "John", "Doe", "1985-05-15", "john.doe@example.com");
		Investor investor2 = new Investor(UUID.randomUUID(), true, "Jane", "Smith", "1990-11-25", "jane.smith@example.com");
		Investor investor3 = new Investor(UUID.randomUUID(), true, "Alice", "Johnson", "1988-02-20", "alice.johnson@example.com");
		Investor investor4 = new Investor(UUID.randomUUID(), true, "Bob", "Brown", "1975-09-10", "bob.brown@example.com");
		Investor investor5 = new Investor(UUID.randomUUID(), true, "Charlie", "Davis", "1983-12-30", "charlie.davis@example.com");
		Investor investor6 = new Investor(UUID.randomUUID(), true, "Diana", "Garcia", "1995-03-05", "diana.garcia@example.com");
		Investor investor7 = new Investor(UUID.randomUUID(), true, "Eve", "Martinez", "1992-07-18", "eve.martinez@example.com");
		Investor investor8 = new Investor(UUID.randomUUID(), true, "Frank", "Miller", "1981-04-25", "frank.miller@example.com");
		Investor investor9 = new Investor(UUID.randomUUID(), true, "Grace", "Wilson", "1986-11-15", "grace.wilson@example.com");
		Investor investor10 = new Investor(UUID.randomUUID(), true, "Hank", "Moore", "1993-01-10", "hank.moore@example.com");

		List<Investor> investors = Arrays.asList(investor1, investor2, investor3, investor4, investor5, investor6, investor7, investor8, investor9, investor10);
		investors.forEach(investorRepository::create);

		// Добавление портфелей
		Portfolio portfolio1 = new Portfolio(UUID.randomUUID(), true, investor1, "John Portfolio", PortfolioType.brokerageAccount, 10000.0);
		Portfolio portfolio2 = new Portfolio(UUID.randomUUID(), true, investor2, "Jane Portfolio", PortfolioType.IIS, 20000.0);
		Portfolio portfolio3 = new Portfolio(UUID.randomUUID(), true, investor3, "Alice Portfolio", PortfolioType.brokerageAccount, 15000.0);
		Portfolio portfolio4 = new Portfolio(UUID.randomUUID(), true, investor4, "Bob Portfolio", PortfolioType.IIS, 25000.0);
		Portfolio portfolio5 = new Portfolio(UUID.randomUUID(), true, investor5, "Charlie Portfolio", PortfolioType.brokerageAccount, 30000.0);
		Portfolio portfolio6 = new Portfolio(UUID.randomUUID(), true, investor6, "Diana Portfolio", PortfolioType.IIS, 35000.0);
		Portfolio portfolio7 = new Portfolio(UUID.randomUUID(), true, investor7, "Eve Portfolio", PortfolioType.brokerageAccount, 40000.0);
		Portfolio portfolio8 = new Portfolio(UUID.randomUUID(), true, investor8, "Frank Portfolio", PortfolioType.IIS, 45000.0);
		Portfolio portfolio9 = new Portfolio(UUID.randomUUID(), true, investor9, "Grace Portfolio", PortfolioType.brokerageAccount, 50000.0);
		Portfolio portfolio10 = new Portfolio(UUID.randomUUID(), true, investor10, "Hank Portfolio", PortfolioType.IIS, 55000.0);

		List<Portfolio> portfolios = Arrays.asList(portfolio1, portfolio2, portfolio3, portfolio4, portfolio5, portfolio6, portfolio7, portfolio8, portfolio9, portfolio10);
		portfolios.forEach(portfolioRepository::create);

		// Добавление ценных бумаг
		Security security1 = new Security(UUID.randomUUID(), true, "Apple Inc.", SecurityType.Stock, "AAPL", 150);
		Security security2 = new Security(UUID.randomUUID(), true, "Google LLC", SecurityType.Stock, "GOOGL", 2800);
		Security security3 = new Security(UUID.randomUUID(), true, "Amazon.com", SecurityType.Stock, "AMZN", 3500);
		Security security4 = new Security(UUID.randomUUID(), true, "Microsoft Corp.", SecurityType.Stock, "MSFT", 250);
		Security security5 = new Security(UUID.randomUUID(), true, "Tesla Inc.", SecurityType.Stock, "TSLA", 700);
		Security security6 = new Security(UUID.randomUUID(), true, "Facebook Inc.", SecurityType.Stock, "FB", 350);
		Security security7 = new Security(UUID.randomUUID(), true, "Netflix Inc.", SecurityType.Stock, "NFLX", 500);
		Security security8 = new Security(UUID.randomUUID(), true, "NVIDIA Corp.", SecurityType.Stock, "NVDA", 600);
		Security security9 = new Security(UUID.randomUUID(), true, "Adobe Inc.", SecurityType.Stock, "ADBE", 550);
		Security security10 = new Security(UUID.randomUUID(), true, "Intel Corp.", SecurityType.Stock, "INTC", 60);

		List<Security> securities = Arrays.asList(security1, security2, security3, security4, security5, security6, security7, security8, security9, security10);
		securities.forEach(securityRepository::create);

		// Добавление позиций
		Position position1 = new Position(UUID.randomUUID(), true, portfolio1, security1, 100, 150.0, Timestamp.valueOf("2024-07-01 10:00:00"));
		Position position2 = new Position(UUID.randomUUID(), true, portfolio2, security2, 200, 2800.0, Timestamp.valueOf("2024-07-01 11:00:00"));
		Position position3 = new Position(UUID.randomUUID(), true, portfolio3, security3, 150, 3500.0, Timestamp.valueOf("2024-07-02 12:00:00"));
		Position position4 = new Position(UUID.randomUUID(), true, portfolio4, security4, 250, 250.0, Timestamp.valueOf("2024-07-03 13:00:00"));
		Position position5 = new Position(UUID.randomUUID(), true, portfolio5, security5, 300, 700.0, Timestamp.valueOf("2024-07-04 14:00:00"));
		Position position6 = new Position(UUID.randomUUID(), true, portfolio6, security6, 350, 350.0, Timestamp.valueOf("2024-07-05 15:00:00"));
		Position position7 = new Position(UUID.randomUUID(), true, portfolio7, security7, 400, 500.0, Timestamp.valueOf("2024-07-06 16:00:00"));
		Position position8 = new Position(UUID.randomUUID(), true, portfolio8, security8, 450, 600.0, Timestamp.valueOf("2024-07-07 17:00:00"));
		Position position9 = new Position(UUID.randomUUID(), true, portfolio9, security9, 500, 550.0, Timestamp.valueOf("2024-07-08 18:00:00"));
		Position position10 = new Position(UUID.randomUUID(), true, portfolio10, security10, 600, 60.0, Timestamp.valueOf("2024-07-09 19:00:00"));

		List<Position> positions = Arrays.asList(position1, position2, position3, position4, position5, position6, position7, position8, position9, position10);
		positions.forEach(positionRepository::create);

		// Добавление транзакций
		TransactionSecurity transaction1 = new TransactionSecurity(UUID.randomUUID(), true, investor1, investor2, security1, TransactionType.SELL, Timestamp.valueOf("2024-07-15 09:00:00"));
		TransactionSecurity transaction2 = new TransactionSecurity(UUID.randomUUID(), true, investor2, investor1, security2, TransactionType.BUY, Timestamp.valueOf("2024-07-15 10:00:00"));
		TransactionSecurity transaction3 = new TransactionSecurity(UUID.randomUUID(), true, investor3, investor4, security3, TransactionType.SELL, Timestamp.valueOf("2024-07-16 11:00:00"));
		TransactionSecurity transaction4 = new TransactionSecurity(UUID.randomUUID(), true, investor4, investor3, security4, TransactionType.BUY, Timestamp.valueOf("2024-07-16 12:00:00"));
		TransactionSecurity transaction5 = new TransactionSecurity(UUID.randomUUID(), true, investor5, investor6, security5, TransactionType.SELL, Timestamp.valueOf("2024-07-17 13:00:00"));
		TransactionSecurity transaction6 = new TransactionSecurity(UUID.randomUUID(), true, investor6, investor5, security6, TransactionType.BUY, Timestamp.valueOf("2024-07-17 14:00:00"));
		TransactionSecurity transaction7 = new TransactionSecurity(UUID.randomUUID(), true, investor7, investor8, security7, TransactionType.SELL, Timestamp.valueOf("2024-07-18 15:00:00"));
		TransactionSecurity transaction8 = new TransactionSecurity(UUID.randomUUID(), true, investor8, investor7, security8, TransactionType.BUY, Timestamp.valueOf("2024-07-18 16:00:00"));
		TransactionSecurity transaction9 = new TransactionSecurity(UUID.randomUUID(), true, investor9, investor10, security9, TransactionType.SELL, Timestamp.valueOf("2024-07-19 17:00:00"));
		TransactionSecurity transaction10 = new TransactionSecurity(UUID.randomUUID(), true, investor10, investor9, security10, TransactionType.BUY, Timestamp.valueOf("2024-07-19 18:00:00"));

		List<TransactionSecurity> transactions = Arrays.asList(transaction1, transaction2, transaction3, transaction4, transaction5, transaction6, transaction7, transaction8, transaction9, transaction10);
		transactions.forEach(transactionSecurityRepository::create);
	}
}
