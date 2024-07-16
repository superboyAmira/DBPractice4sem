package ru.zakharenko.dbpractice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zakharenko.dbpractice.domain.Position;
import ru.zakharenko.dbpractice.service.PositionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/positions")
public class PositionController {
	private final PositionService positionService;

	@Autowired
	public PositionController(PositionService positionService) {
		this.positionService = positionService;
	}

	@Operation(summary = "Get all positions")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found all positions")
	})
	@GetMapping("/")
	public ResponseEntity<List<Position>> getAll() {
		return ResponseEntity.ok(positionService.getAll());
	}

	@Operation(summary = "Get a position by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the position"),
			@ApiResponse(responseCode = "404", description = "Position not found")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Position> getPositionById(@Parameter(description = "ID of the position") @PathVariable UUID id) {
		Position position = positionService.getByUUID(id);
		if (position == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(position);
	}

	@Operation(summary = "Create a new position")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Position created")
	})
	@PostMapping("/")
	public ResponseEntity<Position> createPosition(@RequestBody Position position) {
		Position createdPosition = positionService.createEntity(position);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPosition);
	}

	@Operation(summary = "Update a position")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Position updated"),
			@ApiResponse(responseCode = "404", description = "Position not found")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Position> updatePosition(@Parameter(description = "ID of the position") @PathVariable UUID id, @RequestBody Position position) {
		Position existingPosition = positionService.getByUUID(id);
		if (existingPosition == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		position.setId(id);
		Position updatedPosition = positionService.update(position);
		return ResponseEntity.ok(updatedPosition);
	}

	@Operation(summary = "Deactivate a position")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Position deactivated"),
			@ApiResponse(responseCode = "404", description = "Position not found"),
			@ApiResponse(responseCode = "204", description = "Position is already inactive")
	})
	@PutMapping("/{id}/deactivate")
	public ResponseEntity<Position> deactivatePosition(@Parameter(description = "ID of the position") @PathVariable UUID id) {
		Position existingPosition = positionService.getByUUID(id);
		if (existingPosition == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (!existingPosition.getStatus()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		positionService.changeVisible(existingPosition);
		return ResponseEntity.ok(existingPosition);
	}

	@Operation(summary = "Activate a position")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Position activated"),
			@ApiResponse(responseCode = "404", description = "Position not found"),
			@ApiResponse(responseCode = "403", description = "Position is already active")
	})
	@PutMapping("/{id}/activate")
	public ResponseEntity<Position> activatePosition(@Parameter(description = "ID of the position") @PathVariable UUID id) {
		Position existingPosition = positionService.getByUUID(id);
		if (existingPosition == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (existingPosition.getStatus()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		positionService.changeVisible(existingPosition);
		return ResponseEntity.ok(existingPosition);
	}
}
