package com.ssafy.happyhouse.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseJoinDto;
import com.ssafy.happyhouse.service.HouseJoinService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2021")
public class HappyHouseController {

	public static final Logger logger = LoggerFactory.getLogger(HappyHouseController.class);

	@Autowired
	private HouseJoinService houseJoinService;

	// ================housejoin start====================

	@ApiOperation(value = "모든 house.", response = List.class)
	@RequestMapping(value = "/housedeallist", method = RequestMethod.GET)
	public ResponseEntity<List<HouseJoinDto>> housedeallist() throws Exception {
		logger.info("1-------------housedeallist-----------------------------" + new Date());
		List<HouseJoinDto> emps = houseJoinService.searchAll();
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<HouseJoinDto>>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = "house 검색", response = HouseJoinDto.class)
	@RequestMapping(value = "/housedealsearch/{no}", method = RequestMethod.GET)
	public ResponseEntity<HouseJoinDto> housedealsearch(@PathVariable int no) throws Exception {
		logger.info("1-------------housedealsearch-----------------------------" + new Date());
		HouseJoinDto emps = houseJoinService.search(no);
		if (emps == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<HouseJoinDto>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = "Apt로 검색", response = List.class)
	@RequestMapping(value = "/aptsearch/{aptname}", method = RequestMethod.GET)
	public ResponseEntity<List<HouseJoinDto>> aptsearch(@PathVariable String aptname) throws Exception {
		logger.info("1-------------aptsearch-----------------------------" + new Date());
		List<HouseJoinDto> emps = houseJoinService.searchApt(aptname);
		if (emps == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<HouseJoinDto>>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = "dong으로 검색", response = List.class)
	@RequestMapping(value = "/dongsearch/{dong}", method = RequestMethod.GET)
	public ResponseEntity<List<HouseJoinDto>> dongsearch(@PathVariable String dong) throws Exception {
		logger.info("1-------------housedealsearch-----------------------------" + new Date());
		List<HouseJoinDto> emps = houseJoinService.searchDong(dong);
		if (emps == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<HouseJoinDto>>(emps, HttpStatus.OK);
	}

	// ================housejoin end======================

}
