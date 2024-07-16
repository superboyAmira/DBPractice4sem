package ru.zakharenko.dbpractice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zakharenko.dbpractice.DTO.LiquidityReport;
import ru.zakharenko.dbpractice.DTO.PortfolioRecommendations;
import ru.zakharenko.dbpractice.domain.Portfolio;
import ru.zakharenko.dbpractice.service.PortfolioService;
import ru.zakharenko.dbpractice.domainService.ScenarioTwoService;
import ru.zakharenko.dbpractice.domainService.ScenarioThreeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {
	private final PortfolioService portfolioService;
	private final ScenarioTwoService scenarioTwoService;
	private final ScenarioThreeService scenarioThreeService;

	@Autowired
	public PortfolioController(PortfolioService portfolioService, ScenarioTwoService scenarioTwoService, ScenarioThreeService scenarioThreeService) {
		this.portfolioService = portfolioService;
		this.scenarioTwoService = scenarioTwoService;
		this.scenarioThreeService = scenarioThreeService;
	}

	@Operation(summary = "Get all portfolios")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found all portfolios")
	})
	@GetMapping("/")
	public ResponseEntity<List<Portfolio>> getAll() {
		return ResponseEntity.ok(portfolioService.getAll());
	}

	@Operation(summary = "Get a portfolio by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the portfolio"),
			@ApiResponse(responseCode = "404", description = "Portfolio not found")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Portfolio> getPortfolioById(@Parameter(description = "ID of the portfolio") @PathVariable UUID id) {
		Portfolio portfolio = portfolioService.getByUUID(id);
		if (portfolio == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(portfolio);
	}

	@Operation(summary = "Create a new portfolio")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Portfolio created")
	})
	@PostMapping("/")
	public ResponseEntity<Portfolio> createPortfolio(@RequestBody Portfolio portfolio) {
		Portfolio createdPortfolio = portfolioService.createEntity(portfolio);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPortfolio);
	}

	@Operation(summary = "Update a portfolio")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Portfolio updated"),
			@ApiResponse(responseCode = "404", description = "Portfolio not found")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Portfolio> updatePortfolio(@Parameter(description = "ID of the portfolio") @PathVariable UUID id, @RequestBody Portfolio portfolio) {
		Portfolio existingPortfolio = portfolioService.getByUUID(id);
		if (existingPortfolio == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		portfolio.setId(id);
		Portfolio updatedPortfolio = portfolioService.update(portfolio);
		return ResponseEntity.ok(updatedPortfolio);
	}

	@Operation(summary = "Deactivate a portfolio")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Portfolio deactivated"),
			@ApiResponse(responseCode = "404", description = "Portfolio not found"),
			@ApiResponse(responseCode = "204", description = "Portfolio is already inactive")
	})
	@PutMapping("/{id}/deactivate")
	public ResponseEntity<Portfolio> deactivatePortfolio(@Parameter(description = "ID of the portfolio") @PathVariable UUID id) {
		Portfolio existingPortfolio = portfolioService.getByUUID(id);
		if (existingPortfolio == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (!existingPortfolio.getStatus()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		portfolioService.changeVisible(existingPortfolio);
		return ResponseEntity.ok(existingPortfolio);
	}

	@Operation(summary = "Activate a portfolio")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Portfolio activated"),
			@ApiResponse(responseCode = "404", description = "Portfolio not found"),
			@ApiResponse(responseCode = "403", description = "Portfolio is already active")
	})
	@PutMapping("/{id}/activate")
	public ResponseEntity<Portfolio> activatePortfolio(@Parameter(description = "ID of the portfolio") @PathVariable UUID id) {
		Portfolio existingPortfolio = portfolioService.getByUUID(id);
		if (existingPortfolio == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (existingPortfolio.getStatus()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		portfolioService.changeVisible(existingPortfolio);
		return ResponseEntity.ok(existingPortfolio);
	}

	@Operation(summary = "Get diversification advice for a portfolio")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found diversification advice"),
			@ApiResponse(responseCode = "404", description = "Portfolio not found")
	})
	@GetMapping("/{id}/diversify-advices")
	public ResponseEntity<PortfolioRecommendations> getDiversifyAdvices(@Parameter(description = "ID of the portfolio") @PathVariable UUID id) {
		Portfolio portfolio = portfolioService.getByUUID(id);
		if (portfolio == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		PortfolioRecommendations recommendations = scenarioTwoService.diversifyAdvices(portfolio);
		return ResponseEntity.ok(recommendations);
	}

	@Operation(summary = "Get liquidity report for a portfolio")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found liquidity report"),
			@ApiResponse(responseCode = "404", description = "Portfolio not found")
	})
	@GetMapping("/{id}/liquidity-report")
	public ResponseEntity<LiquidityReport> getLiquidityReport(@Parameter(description = "ID of the portfolio") @PathVariable UUID id) {
		Portfolio portfolio = portfolioService.getByUUID(id);
		if (portfolio == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		LiquidityReport liquidityReport = scenarioThreeService.getLiquidityReport(portfolio);
		return ResponseEntity.ok(liquidityReport);
	}
}
