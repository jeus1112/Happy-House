package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.ChartDto;
import com.ssafy.happyhouse.dto.StationDto;

@Repository
public class StationDaoImpl implements StationDao {

	private String ns = "com.ssafy.happyhouse.dao.StationMapper.";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<StationDto> searchAll() throws Exception {
		return sqlSession.selectList(ns + "searchAll");
	}

	@Override
	public List<ChartDto> getCount() throws Exception {
		return sqlSession.selectList(ns + "getCount");
	}

	@Override
	public int getTotalCount() throws SQLException {
		return sqlSession.selectOne(ns + "getTotalCount");
	}

}
