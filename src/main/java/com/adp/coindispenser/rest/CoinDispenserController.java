package com.adp.coindispenser.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.adp.coindispenser.bo.CoinBO;
import com.adp.coindispenser.boservice.ICoinDispenserBOService;
import com.adp.coindispenser.responsejson.CoinResponseJson;
import com.adp.coindispenser.responsejson.DispenseResponseJson;
import com.adp.coindispenser.responsejson.mapper.MapCoinBOToRespone;
import com.adp.coindispenser.rest.validator.CoinDispenserRequestValidator;

@RestController
public class CoinDispenserController implements ICoinDispenserController{
	private static final Logger LOGGER = LoggerFactory.getLogger(CoinDispenserController.class);

	@Autowired
	private ICoinDispenserBOService boService;
	
	@Autowired
	private MapCoinBOToRespone responseJsonMapper;
	
	public ResponseEntity<DispenseResponseJson> dispenseCoin(int amount, String leastNumberOfCoins ) {
		
		LOGGER.info("Incoming Request amount: {} leastNumberOfCoins : {} ", amount,  leastNumberOfCoins);
		
		CoinDispenserRequestValidator.validateDispenseRequest(amount, leastNumberOfCoins );

		List<CoinBO> coinBOList = boService.displenseCoins(amount, Boolean.parseBoolean(leastNumberOfCoins));
		List<CoinResponseJson> coinResponseJsonList = responseJsonMapper.map(coinBOList);
		DispenseResponseJson dispenseResponseJson =  new DispenseResponseJson();
		dispenseResponseJson.addCoins(coinResponseJsonList);
		
		LOGGER.info("Outgoing Response " + dispenseResponseJson);

		return new ResponseEntity<DispenseResponseJson>(dispenseResponseJson, HttpStatus.OK);
	}

}
