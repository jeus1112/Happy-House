package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.NoticeDto;

public interface NoticeDao {

	public void writeArticle(NoticeDto noticeDto) throws SQLException;

	public List<NoticeDto> listArticle() throws SQLException;

	public int getTotalCount() throws SQLException;

	public NoticeDto getArticle(int no) throws SQLException;

	public boolean modifyArticle(NoticeDto noticeDto) throws SQLException;

	public boolean deleteArticle(int no) throws SQLException;

	public int afterAdd() throws Exception;

	public List<NoticeDto> findArticleById(String userid) throws Exception;

	public List<NoticeDto> findArticleByTitle(String title) throws Exception;

}
