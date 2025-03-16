package io.eventuate.tram.examples.customersandorders.springauthorizationserver.customers_and_orders_spring_authorization_server;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomersAndOrdersSpringAuthorizationServerApplicationTests {

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void healthEndpointShouldReturn200() {
		RestAssured
			.given()
				.redirects().follow(false)
			.when()
				.get("/actuator/health")
			.then()
				.statusCode(200);
	}



}
