package com.adp.coindispenser.responsejson.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.adp.coindispenser.bo.CoinBO;
import com.adp.coindispenser.responsejson.CoinResponseJson;

@Component
public class MapCoinBOToRespone {
	
	public List<CoinResponseJson> map(List<CoinBO> coinBOList) {
		List<CoinResponseJson> coinResponseJsonList = null;
		if (coinBOList != null && !coinBOList.isEmpty()) {
			
			 coinResponseJsonList =  coinBOList.stream()
			.map(coinBO -> new CoinResponseJson(coinBO.getCoinType(), coinBO.getNumberOfCoins())).collect(Collectors.toList());
		
		}
		
		return coinResponseJsonList;
	}
}
