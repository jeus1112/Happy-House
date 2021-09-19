package com.ssafy.happyhouse.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.BoardDao;
import com.ssafy.happyhouse.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

	@Autowired
	private BoardDao boardDao;

	@Override
	@Transactional
	public int writeArticle(BoardDto boardDto) throws Exception {
		boardDao.writeArticle(boardDto);
		return boardDao.afterAdd();
	}

	@Override
	@Transactional
	public List<BoardDto> listArticle() throws Exception {
		return boardDao.listArticle();
	}

	@Override
	@Transactional
	public BoardDto getArticle(int no) throws Exception {
		return boardDao.getArticle(no);
	}

	@Override
	@Transactional
	public boolean modifyArticle(BoardDto boardDto) throws Exception {
		return boardDao.modifyArticle(boardDto);
	}

	@Override
	@Transactional
	public boolean deleteArticle(int no) throws Exception {
		return boardDao.deleteArticle(no);
	}

	@Override
	@Transactional
	public List<BoardDto> findArticleById(String userid) throws Exception {
		return boardDao.findArticleById(userid);
	}

	@Override
	@Transactional
	public List<BoardDto> findArticleByTitle(String title) throws Exception {
		return boardDao.findArticleByTitle(title);
	}

}
