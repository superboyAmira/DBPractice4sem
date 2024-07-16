package ru.zakharenko.dbpractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zakharenko.dbpractice.DTO.ProfitStatistic;
import ru.zakharenko.dbpractice.domain.Investor;
import ru.zakharenko.dbpractice.domain.Portfolio;
import ru.zakharenko.dbpractice.domainService.ScenarioOneService;
import ru.zakharenko.dbpractice.service.InvestorService;
import ru.zakharenko.dbpractice.service.PortfolioService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/investors/")
public class InvestorController {
	private final ScenarioOneService scenarioOneService;
	private final PortfolioService portfolioService;
	private final InvestorService investorService;

	@Autowired
	public InvestorController(ScenarioOneService scenarioOneService,
	                          PortfolioService portfolioService,
	                          InvestorService investorService) {
		this.scenarioOneService = scenarioOneService;
		this.portfolioService = portfolioService;
		this.investorService = investorService;
	}

	@GetMapping("/{inv_id}/profit/all-portfolios")
	public ResponseEntity<ProfitStatistic> getProfitAllPortfolios(@PathVariable UUID id) {
		Investor investor = investorService.getByUUID(id);
		if (investor == null) {
			return ResponseEntity.notFound().build();
		}
		if (!investor.getStatus()) {
			return ResponseEntity.noContent().build();
		}
		ProfitStatistic profitStatistic = scenarioOneService.getProfitAllPortfolio(investor);
		return ResponseEntity.ok(profitStatistic);
	}

	@GetMapping("/{inv_id}/profit/single-portfolio/{id}")
	public ResponseEntity<ProfitStatistic> getProfitSinglePortfolio(@PathVariable("inv_id") UUID inv_id, @PathVariable("id") UUID id) {
		Portfolio portfolio = portfolioService.getByUUID(id);
		Investor investor = investorService.getByUUID(inv_id);
		if (portfolio == null) {
			return ResponseEntity.notFound().build();
		}
		if (!investor.getStatus()) {
			return ResponseEntity.noContent().build();
		}
		if (portfolio.getInvestor() != investor) {
			return ResponseEntity.badRequest().build();
		}
		ProfitStatistic profitStatistic = scenarioOneService.getProfitSinglePortfolio(portfolio);
		return ResponseEntity.ok(profitStatistic);
	}

	@GetMapping("/")
	public ResponseEntity<List<Investor>> getAll() {
		return ResponseEntity.ok(investorService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Investor> getInvestorById(@PathVariable UUID id) {
		Investor investor = investorService.getByUUID(id);
		if (investor == null) {
			ResponseEntity.notFound().build();
		}
		if (!investor.getStatus()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(investor);
	}

	@PostMapping("/")
	public ResponseEntity<Investor> createInvestor(@RequestBody Investor investor) {
		if (!investor.getStatus()) {
			return ResponseEntity.noContent().build();
		}
		Investor createdInvestor = investorService.createEntity(investor);
		return ResponseEntity.ok(createdInvestor);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Investor> updateInvestor(@PathVariable UUID id, @RequestBody Investor investor) {
		Investor existingInvestor = investorService.getByUUID(id);
		if (existingInvestor == null) {
			return ResponseEntity.notFound().build();
		}
		if (!investor.getStatus()) {
			return ResponseEntity.noContent().build();
		}
		investor.setId(id);
		Investor updatedInvestor = investorService.update(investor);
		return ResponseEntity.ok(updatedInvestor);
	}

	@PutMapping("/{id}/deactivate")
	public ResponseEntity<Investor> deactivateInvestor(@PathVariable UUID id) {
		Investor existingInvestor = investorService.getByUUID(id);
		if (existingInvestor == null) {
			return ResponseEntity.notFound().build();
		}
		if (!existingInvestor.getStatus()) {
			return ResponseEntity.noContent().build();
		}
		investorService.changeVisible(existingInvestor);
		return ResponseEntity.ok(existingInvestor);
	}

	@PutMapping("/{id}/activate")
	public ResponseEntity<Investor> activateInvestor(@PathVariable UUID id) {
		Investor existingInvestor = investorService.getByUUID(id);
		if (existingInvestor == null) {
			return ResponseEntity.notFound().build();
		}
		if (existingInvestor.getStatus()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		investorService.changeVisible(existingInvestor);
		return ResponseEntity.ok(existingInvestor);
	}
}

