package ru.zakharenko.dbpractice.repository.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.zakharenko.dbpractice.service.DataInitializationService;

@Component
public class DataInitializer implements CommandLineRunner {

	private final DataInitializationService dataInitializationService;

	public DataInitializer(DataInitializationService dataInitializationService) {
		this.dataInitializationService = dataInitializationService;
	}

	@Override
	public void run(String... args) throws Exception {
		dataInitializationService.initializeData();
	}
}