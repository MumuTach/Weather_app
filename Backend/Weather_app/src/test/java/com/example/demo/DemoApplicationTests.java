package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	private static String url;

	@LocalServerPort
	private int localServerPort;

	private WebTestClient webTestClient;

	@BeforeEach
	void setUp() {
		url = "http://localhost:" + localServerPort + "/api/weather";
		webTestClient = WebTestClient.bindToServer().baseUrl(url).build();
	}

	@Test
	void integrationTest_For_Finding_forcast_of_a_city() {
		String city = "Paris";

		webTestClient.get()
				.uri(url+ "/" + city)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.city.name").isEqualTo(city)
				.jsonPath("$.list").isArray();
	}

}
