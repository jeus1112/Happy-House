package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.NoticeDto;

@Repository
public class NoticeDaoImpl implements NoticeDao {

	private String ns = "com.ssafy.happyhouse.dao.NoticeMapper.";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void writeArticle(NoticeDto noticeDto) throws SQLException {
		sqlSession.insert(ns + "writeArticle", noticeDto);
	}

	@Override
	public List<NoticeDto> listArticle() throws SQLException {
		return sqlSession.selectList(ns + "listArticle");
	}

	@Override
	public int getTotalCount() throws SQLException {
		return sqlSession.selectOne(ns + "getTotalCount");
	}

	@Override
	public NoticeDto getArticle(int no) throws SQLException {
		return sqlSession.selectOne(ns + "getArticle", no);
	}

	@Override
	public boolean modifyArticle(NoticeDto noticeDto) throws SQLException {
		sqlSession.update(ns + "modifyArticle", noticeDto);
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
	public List<NoticeDto> findArticleById(String userid) throws Exception {
		return sqlSession.selectList(ns + "findArticleById", userid);
	}

	@Override
	public List<NoticeDto> findArticleByTitle(String title) throws Exception {
		return sqlSession.selectList(ns + "findArticleByTitle", title);
	}

}
