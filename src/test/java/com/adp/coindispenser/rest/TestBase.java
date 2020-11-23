package com.adp.coindispenser.rest;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.adp.coindispenser.CoindispenserApplication;
import com.adp.coindispenser.config.DBConfiguration;

@SpringBootTest(classes = {CoindispenserApplication.class, DBConfiguration.class, })
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class TestBase {
	
	public static final String CREATE_DB_SCHEMA_SCRIPT = "cd_schema.sql";
	public static final String INSERT_DATA_SCRIPT = "cd_data.sql";
	public static final String RESET_DATA_SCRIPT = "/cd_reset.sql";


}
