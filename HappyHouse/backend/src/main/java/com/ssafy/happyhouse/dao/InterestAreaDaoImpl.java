package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.InterestAreaDto;

@Repository
public class InterestAreaDaoImpl implements InterestAreaDao {

	private String ns = "com.ssafy.happyhouse.dao.InterestAreaMapper.";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void addArea(InterestAreaDto interestAreaDto) throws Exception {
		sqlSession.insert(ns + "addArea", interestAreaDto);
	}

	@Override
	public List<InterestAreaDto> searchAll() throws Exception {
		return sqlSession.selectList(ns + "searchAll");
	}

	@Override
	public int getTotalCount() throws SQLException {
		return sqlSession.selectOne(ns + "getTotalCount");
	}

	@Override
	public List<InterestAreaDto> search(String userid) throws Exception {
		return sqlSession.selectList(ns + "search", userid);
	}

	@Override
	public boolean updateArea(InterestAreaDto interestAreaDto) throws Exception {
		sqlSession.update(ns + "updateArea", interestAreaDto);
		return true;
	}

	@Override
	public boolean deleteArea(int no) throws Exception {
		sqlSession.delete(ns + "deleteArea", no);
		return true;
	}

	@Override
	public int afterAdd() throws Exception {
		return sqlSession.selectOne(ns + "afterAdd");
	}

	@Override
	public InterestAreaDto getArticle(int no) throws SQLException {
		return sqlSession.selectOne(ns + "getArticle", no);
	}

}
