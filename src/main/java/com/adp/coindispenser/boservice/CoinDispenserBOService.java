package com.adp.coindispenser.boservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.adp.coindispenser.bo.CoinBO;
import com.adp.coindispenser.exception.CDApplicationException;
import com.adp.coindispenser.exception.CDChangeNotAvailabeException;
import com.adp.coindispenser.exception.CDDatabaseException;
import com.adp.coindispenser.exception.ErrorCode;
import com.adp.coindispenser.exception.ErrorType;
import com.adp.coindispenser.repository.ICoinDispenserDAO;
import com.adp.coindispenser.repository.dto.CoinDTO;

@Component
public class CoinDispenserBOService implements ICoinDispenserBOService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoinDispenserBOService.class);

	@Autowired
	private ICoinDispenserDAO dao;

	@Transactional
	public List<CoinBO> displenseCoins(int requestedAmount, boolean sortOrderAsc) {
		LOGGER.info("displenseCoins started requestedAmount: {} sortOrderAsc : {} ", requestedAmount,  sortOrderAsc);
		try {
			// Getting all the available coin type with remainingQuantity from the db
			List<CoinDTO> coinDTOList = dao.getAvailableCoins(sortOrderAsc);

			if(coinDTOList == null || coinDTOList.isEmpty()) {
				throw new CDChangeNotAvailabeException(ErrorCode.CD_BUSINESS_100, ErrorType.BUSINESS, "Change for requested amount not available");
			}
			
			float retrievedTotalAmountFromDb = 0;

			Map<Float, Integer> processedCoinsMap = new HashMap<>();

			if (coinDTOList != null && !coinDTOList.isEmpty()) {
				// Given an amount in Bills that can be (1, 2, 5, 10, 20, 50, 100) change it to
				// coins that are
				// (0.01, 0.05, 0.10, 0.25).
				// The machine needs to assume there is a finite number of coins.

				for (CoinDTO coinDTO : coinDTOList) {

					float type = coinDTO.getType();
					int remNumberOfConinsFromDB = coinDTO.getRemainingQuantity();
					// Checking total coins required for a type for a the remaining required amount
					int requiredNumberOfCoinsForType = (int) ((requestedAmount - retrievedTotalAmountFromDb) / type);
					// if user request 2 and there are 9 .25 cents available take only the requiredNumberOfCoinsForType
					if (requiredNumberOfCoinsForType <= remNumberOfConinsFromDB) {
						retrievedTotalAmountFromDb += type * requiredNumberOfCoinsForType;
						processedCoinsMap.put(type, requiredNumberOfCoinsForType);
					} else {
						// if user request 2 and there are 7 .25 cents available then take the remNumberOfConinsFromDB
						retrievedTotalAmountFromDb += type * remNumberOfConinsFromDB;
						processedCoinsMap.put(type, remNumberOfConinsFromDB);
					}

					if (retrievedTotalAmountFromDb >= requestedAmount) {
						break;
					}
					
				}

				if (retrievedTotalAmountFromDb != requestedAmount) {
					throw new CDChangeNotAvailabeException(ErrorCode.CD_BUSINESS_100, ErrorType.BUSINESS, "Change for requested amount not available");
				}
				// Reduce the quantify from db and send back the response
				dao.updateDispercerWithProcessedQuantity(processedCoinsMap);
			}
			
			List<CoinBO> coinBOList =  createCoinBOList(processedCoinsMap);
			LOGGER.debug("displenseCoins end with coinBOList: {}", coinBOList);

			return coinBOList;
		} catch (CDDatabaseException cde) {
			LOGGER.error("Error while processing displenseCoins Messge:{}", cde.getMessage(), cde);
			throw new CDApplicationException(HttpStatus.INTERNAL_SERVER_ERROR,ErrorCode.CD_APPLICATION_100, ErrorType.APPLICATION, "ApplicationError");
		}

	}

	private List<CoinBO> createCoinBOList(Map<Float, Integer> processedCoinsMap) {
		List<CoinBO> boList = null;
		if (processedCoinsMap != null && !processedCoinsMap.isEmpty()) {

			boList = new ArrayList<>();
			for (Map.Entry<Float, Integer> entry : processedCoinsMap.entrySet()) {
				CoinBO coinBO = new CoinBO();
				coinBO.setCoinType(entry.getKey());
				coinBO.setNumberOfCoins(entry.getValue());

				boList.add(coinBO);
			}
		}
		return boList;
	}

}
