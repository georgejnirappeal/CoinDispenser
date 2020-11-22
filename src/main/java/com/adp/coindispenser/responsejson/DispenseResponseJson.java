package com.adp.coindispenser.responsejson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DispenseResponseJson {
	@JsonProperty("dispensedCoins")
	private List<CoinResponseJson> coins  = new ArrayList<>();

	public List<CoinResponseJson> getCoins() {
		return coins;
	}

	public void addCoin(CoinResponseJson coin) {
		this.coins.add(coin);
	}
	
	public void addCoins(List<CoinResponseJson> coins) {
		if(coins != null && !coins.isEmpty()) {
			this.coins.addAll(coins);
		}
	}

	@Override
	public String toString() {
		return "DispenseResponseJson [coins=" + coins + "]";
	}
}
