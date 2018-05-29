package com.natalia.internship;

import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DatabaseConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public Connection connect() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://elmer.db.elephantsql.com:5432/opbqpwew",
					env.getProperty("databaseUsername"), env.getProperty("databasePassword"));
		} catch (Exception e) {
			e.printStackTrace();
			throw  new RuntimeException("Fatal error");
		}
	}

}
