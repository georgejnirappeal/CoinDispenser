package com.adp.coindispenser.repository;

import java.util.List;
import java.util.Map;

import com.adp.coindispenser.repository.dto.CoinDTO;

public interface ICoinDispenserDAO {
	/**
	 * This method will retrieve the coin type and number of quantity for a type
	 * having number of quantity > 0 in the provided ordering specified in the
	 * parameter
	 * 
	 * @param sortOrderAsc boolean
	 * @return List<CoinDTO>
	 */
	
	public List<CoinDTO> getAvailableCoins(boolean minNumberOfCoins);
	
	/**
	 * This method will subtract the proceed coins from the REMAINING_QUANTITY in
	 * the database for each coin type with a batch operation.
	 * 
	 * @param processedCoinsMap Map<Float, Integer>
	 */
	public void updateDispercerWithProcessedQuantity(Map<Float, Integer> processedCoinsMap);

}
