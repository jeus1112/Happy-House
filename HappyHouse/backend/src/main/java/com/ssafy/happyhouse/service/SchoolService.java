package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.SchoolDto;
import com.ssafy.happyhouse.dto.ChartDto;

public interface SchoolService {

	List<SchoolDto> searchAll() throws Exception;

	List<ChartDto> getCount() throws Exception;

	public int getTotalCount() throws SQLException;
}
