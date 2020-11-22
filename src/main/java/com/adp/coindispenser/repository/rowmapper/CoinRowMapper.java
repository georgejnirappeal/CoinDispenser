package com.adp.coindispenser.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.adp.coindispenser.repository.dto.CoinDTO;

public class CoinRowMapper implements RowMapper<CoinDTO> {

	@Override
	public CoinDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CoinDTO coinDao = new CoinDTO();
		coinDao.setId(rs.getInt("ID"));
		coinDao.setType(rs.getFloat("TYPE"));
		coinDao.setRemainingQuantity(rs.getInt("REMAINING_QUANTITY"));

		return coinDao;
	}

}
