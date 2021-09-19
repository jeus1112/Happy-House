package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.BoardDto;

public interface BoardDao {

	public void writeArticle(BoardDto boardDto) throws SQLException;

	public List<BoardDto> listArticle() throws SQLException;

	public int getTotalCount() throws SQLException;

	public BoardDto getArticle(int no) throws SQLException;

	public boolean modifyArticle(BoardDto boardDto) throws SQLException;

	public boolean deleteArticle(int no) throws SQLException;

	public int afterAdd() throws Exception;

	public List<BoardDto> findArticleById(String userid) throws Exception;

	public List<BoardDto> findArticleByTitle(String title) throws Exception;

}
