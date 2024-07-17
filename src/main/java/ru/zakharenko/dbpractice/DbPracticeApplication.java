package ru.zakharenko.dbpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = "ru.zakharenko.dbpractice")
public class DbPracticeApplication {
	public static void main(String[] args) {
		SpringApplication.run(DbPracticeApplication.class, args);
	}

}
