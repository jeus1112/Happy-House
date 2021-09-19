package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.InterestAreaDto;

public interface InterestAreaDao {

	void addArea(InterestAreaDto interestAreaDto) throws Exception;

	List<InterestAreaDto> searchAll() throws Exception;

	public int getTotalCount() throws SQLException;

	List<InterestAreaDto> search(String userid) throws Exception;

	boolean updateArea(InterestAreaDto interestAreaDto) throws Exception;

	boolean deleteArea(int no) throws Exception;

	public int afterAdd() throws Exception;

	public InterestAreaDto getArticle(int no) throws SQLException;
}
