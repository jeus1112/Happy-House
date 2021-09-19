package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.BoardDto;

public interface BoardService {

	public int writeArticle(BoardDto boardDto) throws Exception;

	public List<BoardDto> listArticle() throws Exception;

	public BoardDto getArticle(int no) throws Exception;

	public boolean modifyArticle(BoardDto boardDto) throws Exception;

	public boolean deleteArticle(int no) throws Exception;

	public List<BoardDto> findArticleById(String userid) throws Exception;

	public List<BoardDto> findArticleByTitle(String title) throws Exception;
}
