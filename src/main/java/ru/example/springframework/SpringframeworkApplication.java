package ru.example.springframework;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class SpringframeworkApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(SpringframeworkApplication.class, args);
		Console.main(args);
	}

}
