package com.adp.coindispenser.rest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.adp.coindispenser.exception.CDChangeNotAvailabeException;
import com.adp.coindispenser.exception.CDInvalidInputException;
import com.adp.coindispenser.responsejson.CoinResponseJson;
import com.adp.coindispenser.responsejson.DispenseResponseJson;

public class CoinDispenserControllerTest extends TestBase {
	
	//Loading the inmemory db with coin type 0.01,0.05,0.10,0.25 with 100 count during the app start up
	@Autowired
	private CoinDispenserController controller;

	@Test 
	@Order(1)
	void testDispenseCoinWithMaxAmount() {
		ResponseEntity<DispenseResponseJson> resp = controller.dispenseCoin(41, "false");
		assertValidResponse(resp);
		List<CoinResponseJson> coins = resp.getBody().getCoins();
		assertEquals(4, coins.size());
		for (CoinResponseJson coin : coins) {
			assertNotNullCoin(coin);
			assertTrue(coin.getNumberOfCoins() == 100);
		}
	}
	
	@Test
	@Order(2)
	void testDispenseCoinWithNotAvailableAmount() {
		Exception exception = assertThrows(CDChangeNotAvailabeException.class, () -> {
			controller.dispenseCoin(1, "false");
		});
		assertNotNull(exception);
		assertEquals(exception.getMessage(), "Change for requested amount not available");
	}
	
	@Test 
	@Order(3)
	@Sql({TestBase.RESET_DATA_SCRIPT})
	//Resetting the inmemory db with coin type 0.01,0.05,0.10,0.25 with 100 count during the app start up
	void testDispenseCoinsMostNumberOfCoins() {
		ResponseEntity<DispenseResponseJson> resp = controller.dispenseCoin(10, "false");
		assertValidResponse(resp);
		List<CoinResponseJson> coins = resp.getBody().getCoins();
		assertEquals(3, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			
			if(coin.getCoinType() == .01f) {
				assertTrue(coin.getNumberOfCoins() == 100);
			}else if (coin.getCoinType() == .05f) {
				assertTrue(coin.getNumberOfCoins() == 100);
			}else if (coin.getCoinType() == .1f) {
				assertTrue(coin.getNumberOfCoins() == 40);
			}
			
		}
		
		resp = controller.dispenseCoin(5, "false");
		
		assertValidResponse(resp);
		coins = resp.getBody().getCoins();
		assertEquals(1, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			
			if(coin.getCoinType() == .1f) {
				assertTrue(coin.getNumberOfCoins() == 50);
			}
			
		}
		
		resp = controller.dispenseCoin(5, "false");
		
		assertValidResponse(resp);
		coins = resp.getBody().getCoins();
		assertEquals(2, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			
			if(coin.getCoinType() == .1f) {
				assertTrue(coin.getNumberOfCoins() == 10);
			}else if(coin.getCoinType() == .25f) {
				assertTrue(coin.getNumberOfCoins() == 16);
			}
			
		}
		
		resp = controller.dispenseCoin(20, "false");
		
		assertValidResponse(resp);
		coins = resp.getBody().getCoins();
		assertEquals(1, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			
			if(coin.getCoinType() == .25f) {
				assertTrue(coin.getNumberOfCoins() == 80);
			}
			
		}
		
		resp = controller.dispenseCoin(1, "false");
		
		assertValidResponse(resp);
		coins = resp.getBody().getCoins();
		assertEquals(1, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			
			if(coin.getCoinType() == .25f) {
				assertTrue(coin.getNumberOfCoins() == 4);
			}
			
		}
		
		Exception exception = assertThrows(CDChangeNotAvailabeException.class, () -> {
			controller.dispenseCoin(1, "false");
		});
		assertNotNull(exception);
		assertEquals(exception.getMessage(), "Change for requested amount not available");
	}

	private void assertValidResponse(ResponseEntity<DispenseResponseJson> resp) {
		assertNotNull(resp);
		assertNotNull(resp.getBody().getCoins());
	}

	private void assertNotNullCoin(CoinResponseJson coin) {
		assertNotNull(coin);
		assertNotNull(coin.getCoinType());
		assertNotNull(coin.getNumberOfCoins());
	}
	
	@Test 
	@Order(4)
	@Sql({TestBase.RESET_DATA_SCRIPT})
	//Resetting the inmemory db with coin type 0.01,0.05,0.10,0.25 with 100 count during the app start up
	void testDispenseCoinsLeastNumberOfCoins() {
		ResponseEntity<DispenseResponseJson> resp = controller.dispenseCoin(10, "true");
		assertValidResponse(resp);
		List<CoinResponseJson> coins = resp.getBody().getCoins();
		assertEquals(1, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			
			if(coin.getCoinType() == .25f) {
				assertTrue(coin.getNumberOfCoins() == 40);
			}
			
		}
		
		resp = controller.dispenseCoin(5, "true");
		
		assertValidResponse(resp);
		coins = resp.getBody().getCoins();
		assertEquals(1, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			
			if(coin.getCoinType() == .25f) {
				assertTrue(coin.getNumberOfCoins() == 20);
			}
			
		}
		
		resp = controller.dispenseCoin(5, "true");
		
		assertValidResponse(resp);
		coins = resp.getBody().getCoins();
		assertEquals(1, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			
			if(coin.getCoinType() == .25f) {
				assertTrue(coin.getNumberOfCoins() == 20);
			}
		}
		
		resp = controller.dispenseCoin(18, "true");
		
		assertValidResponse(resp);
		coins = resp.getBody().getCoins();
		assertEquals(3, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			
			if(coin.getCoinType() == .25f) {
				assertTrue(coin.getNumberOfCoins() == 20);
			}else if(coin.getCoinType() == .1f) {
				assertTrue(coin.getNumberOfCoins() == 100);
			}else if(coin.getCoinType() == .05f) {
				assertTrue(coin.getNumberOfCoins() == 60);
			}
		}
		
		Exception exception = assertThrows(CDChangeNotAvailabeException.class, () -> {
			controller.dispenseCoin(4, "true");
		});
		assertNotNull(exception);
		assertEquals(exception.getMessage(), "Change for requested amount not available");
		
		resp = controller.dispenseCoin(3, "true");
		
		assertValidResponse(resp);
		coins = resp.getBody().getCoins();
		assertEquals(2, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			if(coin.getCoinType() == .05f) {
				assertTrue(coin.getNumberOfCoins() == 40);
			}else if(coin.getCoinType() == .01f) {
				assertTrue(coin.getNumberOfCoins() == 100);
			} 
		}
		
	}
	

	@Test 
	@Order(5)
	@Sql({TestBase.RESET_DATA_SCRIPT})
	//Resetting the inmemory db with coin type 0.01,0.05,0.10,0.25 with 100 count during the app start up
	void testDispenseCoinsNumberOfCoinsInMixedMode() {
		ResponseEntity<DispenseResponseJson> resp = controller.dispenseCoin(10, "true");
		assertValidResponse(resp);
		List<CoinResponseJson> coins = resp.getBody().getCoins();
		assertEquals(1, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			
			if(coin.getCoinType() == .25f) {
				assertTrue(coin.getNumberOfCoins() == 40);
			}
			
		}
		
		resp = controller.dispenseCoin(5, "false");
		
		assertValidResponse(resp);
		coins = resp.getBody().getCoins();
		assertEquals(2, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			
			if(coin.getCoinType() == .01f) {
				assertTrue(coin.getNumberOfCoins() == 100);
			}else if(coin.getCoinType() == .05f) {
				assertTrue(coin.getNumberOfCoins() == 80);
			}
			
		}
		
		resp = controller.dispenseCoin(5, "true");
		
		assertValidResponse(resp);
		coins = resp.getBody().getCoins();
		assertEquals(1, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			
			if(coin.getCoinType() == .25f) {
				assertTrue(coin.getNumberOfCoins() == 20);
			}
		}
		
		resp = controller.dispenseCoin(18, "false");
		
		assertValidResponse(resp);
		coins = resp.getBody().getCoins();
		assertEquals(3, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			
			if(coin.getCoinType() == .25f) {
				assertTrue(coin.getNumberOfCoins() == 28);
			}else if(coin.getCoinType() == .1f) {
				assertTrue(coin.getNumberOfCoins() == 100);
			}else if(coin.getCoinType() == .05f) {
				assertTrue(coin.getNumberOfCoins() == 20);
			}
		}
		
		Exception exception = assertThrows(CDChangeNotAvailabeException.class, () -> {
			controller.dispenseCoin(4, "true");
		});
		assertNotNull(exception);
		assertEquals(exception.getMessage(), "Change for requested amount not available");
		
		resp = controller.dispenseCoin(3, "true");
		
		assertValidResponse(resp);
		coins = resp.getBody().getCoins();
		assertEquals(1, coins.size());
		for (CoinResponseJson coin : coins) {
			
			assertNotNullCoin(coin);
			if(coin.getCoinType() == .25f) {
				assertTrue(coin.getNumberOfCoins() == 12);
			}
		}
		
	}

	@Test
	@Order(20)
	void testDispenseCoinWithInvalidAmount() {
		Exception exception = assertThrows(CDInvalidInputException.class, () -> {
			controller.dispenseCoin(0, "false");
		});
		assertNotNull(exception);
		assertEquals(exception.getMessage(), "Invalid input, amount should be a greater than 0");
	}
	
	@Test
	@Order(21)
	void testDispenseCoinWithInvalidLeastCoins() {
		Exception exception = assertThrows(CDInvalidInputException.class, () -> {
			controller.dispenseCoin(1, "fal");
		});
		assertNotNull(exception);
		assertEquals(exception.getMessage(), "Invalid input, leastNumberOfCoins should be true or false");
	}
}
