package ru.zakharenko.dbpractice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zakharenko.dbpractice.domain.TransactionSecurity;
import ru.zakharenko.dbpractice.service.TransactionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionSecurityController {
	private final TransactionService transactionService;

	@Autowired
	public TransactionSecurityController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@Operation(summary = "Get all transactions")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found all transactions")
	})
	@GetMapping("/")
	public ResponseEntity<List<TransactionSecurity>> getAll() {
		return ResponseEntity.ok(transactionService.getAll());
	}

	@Operation(summary = "Get a transaction by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the transaction"),
			@ApiResponse(responseCode = "404", description = "Transaction not found")
	})
	@GetMapping("/{id}")
	public ResponseEntity<TransactionSecurity> getTransactionById(@Parameter(description = "ID of the transaction") @PathVariable UUID id) {
		TransactionSecurity transactionSecurity = transactionService.getByUUID(id);
		if (transactionSecurity == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(transactionSecurity);
	}

	@Operation(summary = "Create a new transaction")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Transaction created")
	})
	@PostMapping("/")
	public ResponseEntity<TransactionSecurity> createTransaction(@RequestBody TransactionSecurity transactionSecurity) {
		TransactionSecurity createdTransactionSecurity = transactionService.createEntity(transactionSecurity);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTransactionSecurity);
	}

	@Operation(summary = "Update a transaction")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Transaction updated"),
			@ApiResponse(responseCode = "404", description = "Transaction not found")
	})
	@PutMapping("/{id}")
	public ResponseEntity<TransactionSecurity> updateTransaction(@Parameter(description = "ID of the transaction") @PathVariable UUID id, @RequestBody TransactionSecurity transactionSecurity) {
		TransactionSecurity existingTransactionSecurity = transactionService.getByUUID(id);
		if (existingTransactionSecurity == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		transactionSecurity.setId(id);
		TransactionSecurity updatedTransactionSecurity = transactionService.update(transactionSecurity);
		return ResponseEntity.ok(updatedTransactionSecurity);
	}

	@Operation(summary = "Deactivate a transaction")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Transaction deactivated"),
			@ApiResponse(responseCode = "404", description = "Transaction not found"),
			@ApiResponse(responseCode = "204", description = "Transaction is already inactive")
	})
	@PutMapping("/{id}/deactivate")
	public ResponseEntity<TransactionSecurity> deactivateTransaction(@Parameter(description = "ID of the transaction") @PathVariable UUID id) {
		TransactionSecurity existingTransactionSecurity = transactionService.getByUUID(id);
		if (existingTransactionSecurity == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (!existingTransactionSecurity.getStatus()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		transactionService.changeVisible(existingTransactionSecurity);
		return ResponseEntity.ok(existingTransactionSecurity);
	}

	@Operation(summary = "Activate a transaction")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Transaction activated"),
			@ApiResponse(responseCode = "404", description = "Transaction not found"),
			@ApiResponse(responseCode = "403", description = "Transaction is already active")
	})
	@PutMapping("/{id}/activate")
	public ResponseEntity<TransactionSecurity> activateTransaction(@Parameter(description = "ID of the transaction") @PathVariable UUID id) {
		TransactionSecurity existingTransactionSecurity = transactionService.getByUUID(id);
		if (existingTransactionSecurity == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (existingTransactionSecurity.getStatus()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		transactionService.changeVisible(existingTransactionSecurity);
		return ResponseEntity.ok(existingTransactionSecurity);
	}
}
