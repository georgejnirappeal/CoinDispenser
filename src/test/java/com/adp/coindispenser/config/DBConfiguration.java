package com.adp.coindispenser.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.adp.coindispenser.rest.TestBase;

@Configuration
public class DBConfiguration {

	@Bean
	@Profile("test")
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:" + TestBase.CREATE_DB_SCHEMA_SCRIPT)
				.addScript("classpath:" + TestBase.INSERT_DATA_SCRIPT).build();
	}

}