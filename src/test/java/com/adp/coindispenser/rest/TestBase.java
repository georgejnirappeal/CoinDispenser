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

}
