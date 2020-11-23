package com.adp.coindispenser.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.adp.coindispenser.exception.CDDatabaseException;
import com.adp.coindispenser.exception.ErrorCode;
import com.adp.coindispenser.exception.ErrorType;
import com.adp.coindispenser.repository.dto.CoinDTO;
import com.adp.coindispenser.repository.rowmapper.CoinRowMapper;

@Repository
public class CoinDispenserDAO implements ICoinDispenserDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoinDispenserDAO.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String SELECT_ALL_AVAILABLE_COINS = "SELECT ID, TYPE, REMAINING_QUANTITY FROM COIN WHERE REMAINING_QUANTITY > 0 ORDER BY TYPE ";

	private static final String UPDATE_PROCESSED_COINS_QUERY = "UPDATE COIN SET REMAINING_QUANTITY= REMAINING_QUANTITY-:PROCESSED_QUANTITY WHERE TYPE= :TYPE ";

	public List<CoinDTO> getAvailableCoins(boolean minNumberOfCoins) {

		try {
			String sortOrder = minNumberOfCoins ? "DESC" : "ASC";
			return namedParameterJdbcTemplate.query(SELECT_ALL_AVAILABLE_COINS + sortOrder, new CoinRowMapper());
		} catch (DataAccessException dae) {
			 LOGGER.error("getAvailableCoins error while fetching the records ", dae);
             throw new CDDatabaseException(ErrorCode.CD_DATABAE_101, ErrorType.DATABASE, "Exception while accessing the data");
		}
	}
   
	public void updateDispercerWithProcessedQuantity(Map<Float, Integer> processedCoinsMap) {
		if (processedCoinsMap != null && !processedCoinsMap.isEmpty()) {
			try {

				List<SqlParameterSource> namedParameterList = new ArrayList<>();
	
				for (Map.Entry<Float, Integer> entry : processedCoinsMap.entrySet()) {
					SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("TYPE", entry.getKey())
							.addValue("PROCESSED_QUANTITY", entry.getValue());
	
					namedParameterList.add(namedParameters);
				}
				SqlParameterSource[] parameterArray = namedParameterList
						.toArray(new SqlParameterSource[namedParameterList.size()]);
	
				int[] itemsProcessed = namedParameterJdbcTemplate.batchUpdate(UPDATE_PROCESSED_COINS_QUERY, parameterArray);
				
			    if( Arrays.stream(itemsProcessed).filter(result->result!=1).findFirst().isPresent()) {
			    	LOGGER.warn("updateDispercerWithProcessedQuantity batch processing result is null or empty result: {}", itemsProcessed);
			    	throw new CDDatabaseException(ErrorCode.CD_DATABAE_100, ErrorType.DATABASE, "Invalid batch processing status");
			    }
			
			}catch(DataAccessException dae) {
				 LOGGER.error("getAvailableCoins error while fetching the records ", dae);
	             throw new CDDatabaseException(ErrorCode.CD_DATABAE_101, ErrorType.DATABASE, "Exception while accessing the data");
			}
		}
	}

}
