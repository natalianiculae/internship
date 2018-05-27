package com.natalia.internship;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConnection {

	@Bean
	public Connection connect() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://elmer.db.elephantsql.com:5432/opbqpwew",
					"opbqpwew", "7-G0kkAFHz-PGECfWiUQg_oQJ1zLGXmc");
		} catch (Exception e) {
			e.printStackTrace();
			throw  new RuntimeException("Fatal error");
		}
	}

}
