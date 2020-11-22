package com.adp.coindispenser.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adp.coindispenser.responsejson.DispenseResponseJson;

@RequestMapping("/dispense")
public interface ICoinDispenserController {

	@GetMapping("{amount}")
	public ResponseEntity<DispenseResponseJson> dispenseCoin(@PathVariable("amount") int amount,
			@RequestParam(value = "leastNumberOfCoins", defaultValue = "true") String leastNumberOfCoins);
}
