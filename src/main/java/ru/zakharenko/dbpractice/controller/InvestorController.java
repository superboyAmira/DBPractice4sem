package ru.zakharenko.dbpractice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@Operation(summary = "Get profit for all portfolios of an investor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the profit"),
			@ApiResponse(responseCode = "404", description = "Investor not found"),
			@ApiResponse(responseCode = "204", description = "Investor is inactive")
	})
	@GetMapping("/{inv_id}/profit/all-portfolios")
	public ResponseEntity<ProfitStatistic> getProfitAllPortfolios(@Parameter(description = "ID of the investor") @PathVariable UUID id) {
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

	@Operation(summary = "Get profit for a single portfolio of an investor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the profit"),
			@ApiResponse(responseCode = "404", description = "Investor or portfolio not found"),
			@ApiResponse(responseCode = "204", description = "Investor is inactive"),
			@ApiResponse(responseCode = "400", description = "Portfolio does not belong to investor")
	})
	@GetMapping("/{inv_id}/profit/single-portfolio/{id}")
	public ResponseEntity<ProfitStatistic> getProfitSinglePortfolio(@Parameter(description = "ID of the investor") @PathVariable("inv_id") UUID inv_id,
	                                                                @Parameter(description = "ID of the portfolio") @PathVariable("id") UUID id) {
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

	@Operation(summary = "Get all investors")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found all investors")
	})
	@GetMapping("/")
	public ResponseEntity<List<Investor>> getAll() {
		return ResponseEntity.ok(investorService.getAll());
	}

	@Operation(summary = "Get an investor by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the investor"),
			@ApiResponse(responseCode = "404", description = "Investor not found"),
			@ApiResponse(responseCode = "204", description = "Investor is inactive")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Investor> getInvestorById(@Parameter(description = "ID of the investor") @PathVariable UUID id) {
		Investor investor = investorService.getByUUID(id);
		if (investor == null) {
			return ResponseEntity.notFound().build();
		}
		if (!investor.getStatus()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(investor);
	}

	@Operation(summary = "Create a new investor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Investor created")
	})
	@PostMapping("/")
	public ResponseEntity<Investor> createInvestor(@RequestBody Investor investor) {
		if (!investor.getStatus()) {
			return ResponseEntity.noContent().build();
		}
		Investor createdInvestor = investorService.createEntity(investor);
		return ResponseEntity.ok(createdInvestor);
	}

	@Operation(summary = "Update an investor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Investor updated"),
			@ApiResponse(responseCode = "404", description = "Investor not found"),
			@ApiResponse(responseCode = "204", description = "Investor is inactive")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Investor> updateInvestor(@Parameter(description = "ID of the investor") @PathVariable UUID id, @RequestBody Investor investor) {
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

	@Operation(summary = "Deactivate an investor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Investor deactivated"),
			@ApiResponse(responseCode = "404", description = "Investor not found"),
			@ApiResponse(responseCode = "204", description = "Investor is already inactive")
	})
	@PutMapping("/{id}/deactivate")
	public ResponseEntity<Investor> deactivateInvestor(@Parameter(description = "ID of the investor") @PathVariable UUID id) {
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

	@Operation(summary = "Activate an investor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Investor activated"),
			@ApiResponse(responseCode = "404", description = "Investor not found"),
			@ApiResponse(responseCode = "403", description = "Investor is already active")
	})
	@PutMapping("/{id}/activate")
	public ResponseEntity<Investor> activateInvestor(@Parameter(description = "ID of the investor") @PathVariable UUID id) {
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
