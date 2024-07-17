package ru.zakharenko.dbpractice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zakharenko.dbpractice.domain.Security;
import ru.zakharenko.dbpractice.service.SecurityService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/securities")
public class SecurityController {
	private final SecurityService securityService;

	@Autowired
	public SecurityController(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Operation(summary = "Get all securities")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found all securities")
	})
	@GetMapping("/")
	public ResponseEntity<List<Security>> getAll() {
		return ResponseEntity.ok(securityService.getAll());
	}

	@Operation(summary = "Get a security by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the security"),
			@ApiResponse(responseCode = "404", description = "ISecRepository not found")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Security> getSecurityById(@Parameter(description = "ID of the security") @PathVariable UUID id) {
		Security security = securityService.getByUUID(id);
		if (security == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(security);
	}

	@Operation(summary = "Create a new security")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "ISecRepository created")
	})
	@PostMapping("/")
	public ResponseEntity<Security> createSecurity(@RequestBody Security security) {
		Security createdSecurity = securityService.createEntity(security);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdSecurity);
	}

	@Operation(summary = "Update a security")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "ISecRepository updated"),
			@ApiResponse(responseCode = "404", description = "ISecRepository not found")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Security> updateSecurity(@Parameter(description = "ID of the security") @PathVariable UUID id, @RequestBody Security security) {
		Security existingSecurity = securityService.getByUUID(id);
		if (existingSecurity == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		security.setId(id);
		Security updatedSecurity = securityService.update(security);
		return ResponseEntity.ok(updatedSecurity);
	}

	@Operation(summary = "Deactivate a security")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "ISecRepository deactivated"),
			@ApiResponse(responseCode = "404", description = "ISecRepository not found"),
			@ApiResponse(responseCode = "403", description = "ISecRepository is already inactive")
	})
	@PutMapping("/{id}/deactivate")
	public ResponseEntity<Security> deactivateSecurity(@Parameter(description = "ID of the security") @PathVariable UUID id) {
		Security existingSecurity = securityService.getByUUID(id);
		if (existingSecurity == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (!existingSecurity.getStatus()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		securityService.changeVisible(existingSecurity);
		return ResponseEntity.ok(existingSecurity);
	}

	@Operation(summary = "Activate a security")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "ISecRepository activated"),
			@ApiResponse(responseCode = "404", description = "ISecRepository not found"),
			@ApiResponse(responseCode = "403", description = "ISecRepository is already active")
	})
	@PutMapping("/{id}/activate")
	public ResponseEntity<Security> activateSecurity(@Parameter(description = "ID of the security") @PathVariable UUID id) {
		Security existingSecurity = securityService.getByUUID(id);
		if (existingSecurity == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (existingSecurity.getStatus()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		securityService.changeVisible(existingSecurity);
		return ResponseEntity.ok(existingSecurity);
	}
}
