package com.ssafy.happyhouse.dao;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseJoinDto;
import com.ssafy.happyhouse.dto.SearchCondition;

public interface HouseJoinDao {

	List<HouseJoinDto> searchAll();

	HouseJoinDto search(int no);

	int getTotalSearchCount(SearchCondition searchcondition);

	List<HouseJoinDto> searchApt(String aptname);

	List<HouseJoinDto> searchDong(String dong);

}
