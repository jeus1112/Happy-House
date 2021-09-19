package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.ChartDto;
import com.ssafy.happyhouse.dto.StationDto;

public interface StationDao {

	List<StationDto> searchAll() throws Exception;

	List<ChartDto> getCount() throws Exception;

	public int getTotalCount() throws SQLException;

}
