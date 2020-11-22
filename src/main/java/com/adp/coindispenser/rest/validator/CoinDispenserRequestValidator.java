package com.adp.coindispenser.rest.validator;

import com.adp.coindispenser.exception.CDInvalidInputException;
import com.adp.coindispenser.exception.ErrorCode;
import com.adp.coindispenser.exception.ErrorType;

public class CoinDispenserRequestValidator {

	public static void validateDispenseRequest(int amount, String leastNumberOfCoins) {
		if (amount < 1) {
			throw new CDInvalidInputException(ErrorCode.CD_VALIDATION_101, ErrorType.VALIDATION,
					"Invalid input, amount should be a greater than 0");
		} else if (!(String.valueOf(Boolean.TRUE).equalsIgnoreCase(leastNumberOfCoins)
				|| String.valueOf(Boolean.FALSE).equalsIgnoreCase(leastNumberOfCoins))) {
			throw new CDInvalidInputException(ErrorCode.CD_VALIDATION_101, ErrorType.VALIDATION,
					"Invalid input, leastNumberOfCoins should be true or false");
		}
	}

}
