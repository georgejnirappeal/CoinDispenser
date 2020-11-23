package com.adp.coindispenser.boservice;

import java.util.List;

import com.adp.coindispenser.bo.CoinBO;

public interface ICoinDispenserBOService {

	/**
	 * This method will fetch the available coins in the desired order and process
	 * it, if the change for requested amount find the retrieved coins will
	 * decrement from the db by a batch update, otherwise application will throw a
	 * CDChangeNotAvailabeException.
	 * 
	 * @param requestedAmount int
	 * @param sortOrderAsc    boolean
	 * @return List<CoinBO>
	 */
	public List<CoinBO> displenseCoins(int requestedAmount, boolean sortOrderAsc);

}
