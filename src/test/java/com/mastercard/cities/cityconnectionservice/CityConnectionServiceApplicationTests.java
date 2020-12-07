package com.mastercard.cities.cityconnectionservice;

import com.mastercard.cities.cityconnectionservice.controller.CityConnectionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CityConnectionServiceApplicationTests {

	@Autowired
	private CityConnectionController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
