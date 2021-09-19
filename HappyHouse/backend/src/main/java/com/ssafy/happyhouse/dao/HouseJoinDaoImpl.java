package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.HouseJoinDto;
import com.ssafy.happyhouse.dto.SearchCondition;

@Repository
public class HouseJoinDaoImpl implements HouseJoinDao {

	private String ns = "com.ssafy.happyhouse.dao.HousejoinMapper.";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<HouseJoinDto> searchAll() {
		return sqlSession.selectList(ns + "searchAll");
	}

	@Override
	public HouseJoinDto search(int no) {
		return sqlSession.selectOne(ns + "search", no);
	}

	@Override
	public int getTotalSearchCount(SearchCondition searchcondition) {
		return sqlSession.selectOne(ns + "getTotalSearchCount", searchcondition);
	}

	@Override
	public List<HouseJoinDto> searchApt(String aptname) {
		return sqlSession.selectList(ns + "searchApt", aptname);
	}

	@Override
	public List<HouseJoinDto> searchDong(String dong) {
		return sqlSession.selectList(ns + "searchDong", dong);
	}

}
