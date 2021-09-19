package com.ssafy.happyhouse.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.HouseJoinDao;
import com.ssafy.happyhouse.dto.HouseJoinDto;

@Service
public class HouseJoinServiceImpl implements HouseJoinService {

	private static final Logger logger = LoggerFactory.getLogger(HouseJoinServiceImpl.class);

	@Autowired
	private HouseJoinDao houseJoinDao;

	@Override
	public List<HouseJoinDto> searchAll() {
		return houseJoinDao.searchAll();
	}

	@Override
	public HouseJoinDto search(int no) {
		return houseJoinDao.search(no);
	}

	@Override
	public List<HouseJoinDto> searchApt(String aptname) {
		return houseJoinDao.searchApt(aptname);
	}

	@Override
	public List<HouseJoinDto> searchDong(String dong) {
		return houseJoinDao.searchDong(dong);
	}

}
