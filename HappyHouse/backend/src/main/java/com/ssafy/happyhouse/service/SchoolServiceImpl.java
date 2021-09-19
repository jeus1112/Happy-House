package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.SchoolDao;
import com.ssafy.happyhouse.dto.SchoolDto;
import com.ssafy.happyhouse.dto.ChartDto;

@Service
public class SchoolServiceImpl implements SchoolService {
	private static final Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);

	@Autowired
	private SchoolDao schoolDao;

	@Override
	public List<SchoolDto> searchAll() throws Exception {
		return schoolDao.searchAll();
	}

	@Override
	public List<ChartDto> getCount() throws Exception {
		return schoolDao.getCount();
	}

	@Override
	public int getTotalCount() throws SQLException {
		return schoolDao.getTotalCount();
	}

}
