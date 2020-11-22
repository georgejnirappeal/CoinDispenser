package com.adp.coindispenser.repository;

import java.util.List;
import java.util.Map;

import com.adp.coindispenser.repository.dto.CoinDTO;

public interface ICoinDispenserDAO {
	
	public List<CoinDTO> getAvailableCoins(boolean minNumberOfCoins);
	
	public void updateDispercerWithProcessedQuantity(Map<Float, Integer> processedCoinsMap);

}
