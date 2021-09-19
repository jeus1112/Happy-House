package com.ssafy.happyhouse.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.InterestAreaDao;
import com.ssafy.happyhouse.dto.InterestAreaDto;

@Service
public class InterestAreaServiceImpl implements InterestAreaService {
	private static final Logger logger = LoggerFactory.getLogger(InterestAreaServiceImpl.class);

	@Autowired
	private InterestAreaDao interestAreaDao;

	@Override
	@Transactional
	public int addArea(InterestAreaDto interestAreaDto) throws Exception {
		interestAreaDao.addArea(interestAreaDto);
		return interestAreaDao.afterAdd();
	}

	@Override
	@Transactional
	public List<InterestAreaDto> searchAll() throws Exception {
		return interestAreaDao.searchAll();
	}

	@Override
	@Transactional
	public List<InterestAreaDto> search(String userid) throws Exception {
		return interestAreaDao.search(userid);
	}

	@Override
	@Transactional
	public InterestAreaDto getArticle(int no) throws Exception {
		return interestAreaDao.getArticle(no);
	}

	@Override
	@Transactional
	public boolean updateArea(InterestAreaDto interestAreaDto) throws Exception {
		return interestAreaDao.updateArea(interestAreaDto);
	}

	@Override
	@Transactional
	public boolean deleteArea(int no) throws Exception {
		return interestAreaDao.deleteArea(no);
	}

}
