package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.SchoolDto;
import com.ssafy.happyhouse.dto.ChartDto;

public interface SchoolDao {

	List<SchoolDto> searchAll() throws Exception;

	List<ChartDto> getCount() throws Exception;

	public int getTotalCount() throws SQLException;

}
