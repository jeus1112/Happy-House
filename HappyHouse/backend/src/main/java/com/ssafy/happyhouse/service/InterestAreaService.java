package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.InterestAreaDto;

public interface InterestAreaService {

	public int addArea(InterestAreaDto interestAreaDto) throws Exception;

	public List<InterestAreaDto> searchAll() throws Exception;

	List<InterestAreaDto> search(String userid) throws Exception;

	public InterestAreaDto getArticle(int no) throws Exception;

	public boolean updateArea(InterestAreaDto interestAreaDto) throws Exception;

	public boolean deleteArea(int no) throws Exception;

}
