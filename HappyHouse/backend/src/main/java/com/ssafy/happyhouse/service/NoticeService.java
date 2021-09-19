package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.NoticeDto;

public interface NoticeService {

	public int writeArticle(NoticeDto noticeDto) throws Exception;

	public List<NoticeDto> listArticle() throws Exception;

	public NoticeDto getArticle(int no) throws Exception;

	public boolean modifyArticle(NoticeDto noticeDto) throws Exception;

	public boolean deleteArticle(int no) throws Exception;

	public List<NoticeDto> findArticleById(String userid) throws Exception;

	public List<NoticeDto> findArticleByTitle(String title) throws Exception;
}
