package com.ssafy.happyhouse.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.NoticeDao;
import com.ssafy.happyhouse.dto.NoticeDto;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	@Transactional
	public int writeArticle(NoticeDto noticeDto) throws Exception {
		noticeDao.writeArticle(noticeDto);
		return noticeDao.afterAdd();
	}

	@Override
	@Transactional
	public List<NoticeDto> listArticle() throws Exception {
		return noticeDao.listArticle();
	}

	@Override
	@Transactional
	public NoticeDto getArticle(int no) throws Exception {
		return noticeDao.getArticle(no);
	}

	@Override
	@Transactional
	public boolean modifyArticle(NoticeDto noticeDto) throws Exception {
		return noticeDao.modifyArticle(noticeDto);
	}

	@Override
	@Transactional
	public boolean deleteArticle(int no) throws Exception {
		return noticeDao.deleteArticle(no);
	}

	@Override
	@Transactional
	public List<NoticeDto> findArticleById(String userid) throws Exception {
		return noticeDao.findArticleById(userid);
	}

	@Override
	@Transactional
	public List<NoticeDto> findArticleByTitle(String title) throws Exception {
		return noticeDao.findArticleByTitle(title);
	}

}
