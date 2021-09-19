package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseJoinDto;

public interface HouseJoinService {

	List<HouseJoinDto> searchAll();

	HouseJoinDto search(int no);

	List<HouseJoinDto> searchApt(String aptname);

	List<HouseJoinDto> searchDong(String dongName);

}
