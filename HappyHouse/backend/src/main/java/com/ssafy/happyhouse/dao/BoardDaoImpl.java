package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao {

	private String ns = "com.ssafy.happyhouse.dao.BoardMapper.";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void writeArticle(BoardDto boardDto) throws SQLException {
		sqlSession.insert(ns + "writeArticle", boardDto);
	}

	@Override
	public List<BoardDto> listArticle() throws SQLException {
		return sqlSession.selectList(ns + "listArticle");
	}

	@Override
	public int getTotalCount() throws SQLException {
		return sqlSession.selectOne(ns + "getTotalCount");
	}

	@Override
	public BoardDto getArticle(int no) throws SQLException {
		return sqlSession.selectOne(ns + "getArticle", no);
	}

	@Override
	public boolean modifyArticle(BoardDto boardDto) throws SQLException {
		sqlSession.update(ns + "modifyArticle", boardDto);
		return true;
	}

	@Override
	public boolean deleteArticle(int no) throws SQLException {
		sqlSession.delete(ns + "deleteArticle", no);
		return true;
	}

	@Override
	public int afterAdd() throws Exception {
		return sqlSession.selectOne(ns + "afterAdd");
	}

	@Override
	public List<BoardDto> findArticleById(String userid) throws Exception {
		return sqlSession.selectList(ns + "findArticleById", userid);
	}

	@Override
	public List<BoardDto> findArticleByTitle(String title) throws Exception {
		return sqlSession.selectList(ns + "findArticleByTitle", title);
	}

}
