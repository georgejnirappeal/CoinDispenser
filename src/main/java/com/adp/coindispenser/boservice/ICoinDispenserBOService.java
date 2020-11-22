package com.adp.coindispenser.boservice;

import java.util.List;

import com.adp.coindispenser.bo.CoinBO;

public interface ICoinDispenserBOService {

	public List<CoinBO> displenseCoins(int requestedAmount, boolean sortOrderAsc);

}
