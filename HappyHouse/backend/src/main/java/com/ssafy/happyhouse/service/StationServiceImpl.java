package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.StationDao;
import com.ssafy.happyhouse.dto.ChartDto;
import com.ssafy.happyhouse.dto.StationDto;

@Service
public class StationServiceImpl implements StationService {
	private static final Logger logger = LoggerFactory.getLogger(StationServiceImpl.class);

	@Autowired
	private StationDao stationDao;

	@Override
	public List<StationDto> searchAll() throws Exception {
		return stationDao.searchAll();
	}

	@Override
	public List<ChartDto> getCount() throws Exception {
		return stationDao.getCount();
	}

	@Override
	public int getTotalCount() throws SQLException {
		return stationDao.getTotalCount();
	}

}
