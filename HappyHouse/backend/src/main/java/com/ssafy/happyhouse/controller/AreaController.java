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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.ChartDto;
import com.ssafy.happyhouse.dto.InterestAreaDto;
import com.ssafy.happyhouse.dto.SchoolDto;
import com.ssafy.happyhouse.dto.StationDto;
//import com.ssafy.happyhouse.dto.ConvenienceDto;
import com.ssafy.happyhouse.help.BoolResult;
import com.ssafy.happyhouse.help.NumberResult;
//import com.ssafy.happyhouse.service.ConvenienceService;
import com.ssafy.happyhouse.service.InterestAreaService;
import com.ssafy.happyhouse.service.SchoolService;
import com.ssafy.happyhouse.service.StationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2021")
public class AreaController {

	public static final Logger logger = LoggerFactory.getLogger(AreaController.class);

	@Autowired
	private InterestAreaService interestAreaService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private StationService stationService;
	// ================interestArea start====================

	// 전체 리스트
	@ApiOperation(value = "모든 관심지역.", response = List.class)
	@RequestMapping(value = "/interestAreaList", method = RequestMethod.GET)
	public ResponseEntity<List<InterestAreaDto>> interestAreaList() throws Exception {
		logger.info("1-------------interestAreaList-----------------------------" + new Date());
		List<InterestAreaDto> emps = interestAreaService.searchAll();
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InterestAreaDto>>(emps, HttpStatus.OK);
	}

	// 아이디로 리스트 검색
	@ApiOperation(value = "아이디별 관심지역", response = List.class)
	@RequestMapping(value = "/interestUserSearch/{userid}", method = RequestMethod.GET)
	public ResponseEntity<List<InterestAreaDto>> interestUserSearch(@PathVariable String userid) throws Exception {
		logger.info("1-------------interestUserSearch-----------------------------" + new Date());
		List<InterestAreaDto> emps = interestAreaService.search(userid);
		if (emps == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InterestAreaDto>>(emps, HttpStatus.OK);
	}

	// 추가
	@ApiOperation(value = " 관심지역 등록 ", response = NumberResult.class)
	@RequestMapping(value = "/addInterestArea", method = RequestMethod.POST)
	public ResponseEntity<NumberResult> addInterestArea(@RequestBody InterestAreaDto interestAreaDto) throws Exception {
		logger.info("5-------------addInterestArea-----------------------------" + new Date());
		logger.info("5-------------addInterestArea-----------------------------" + interestAreaDto);
		int total = interestAreaService.addArea(interestAreaDto);
		NumberResult nr = new NumberResult();
		nr.setCount(total);
		nr.setName("addInterestArea");
		nr.setState("succ");
		logger.info("5-------------addInterestArea-------------------------" + total);
		if (total <= 0) {
			nr.setCount(-1);
			nr.setName("addInterestArea");
			nr.setState("fail");
			// return new ResponseEntity(HttpStatus.NO_CONTENT);
			return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
		}
		return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
	}

	// 삭제
	@ApiOperation(value = "관심지역 삭제", response = BoolResult.class)
	@RequestMapping(value = "/deleteInterestArea/{no}", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> deleteInterestArea(@PathVariable int no) throws Exception {
		logger.info("1-------------deleteInterestArea-----------------------------" + new Date());
		logger.info("1-------------deleteInterestArea-----------------------------" + no);

		boolean total = interestAreaService.deleteArea(no);
		BoolResult nr = new BoolResult();
		nr.setCount(total);
		nr.setName("deleteInterestArea");
		nr.setState("succ");
		if (!total) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	// 변경
	@ApiOperation(value = " 관심지역 수정 ", response = BoolResult.class)
	@RequestMapping(value = "/updateInterestArea", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> updateInterestArea(@RequestBody InterestAreaDto interestAreaDto)
			throws Exception {
		logger.info("1-------------updateInterestArea-----------------------------" + new Date());
		logger.info("1-------------updateInterestArea-----------------------------" + interestAreaDto);
		boolean total = interestAreaService.updateArea(interestAreaDto);
		BoolResult nr = new BoolResult();
		nr.setCount(total);
		nr.setName("updateInterestArea");
		nr.setState("succ");
		if (!total) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	// ================interestArea end======================

	// ================school start====================
	@ApiOperation(value = "모든 학교.", response = List.class)
	@RequestMapping(value = "/schoolList", method = RequestMethod.GET)
	public ResponseEntity<List<SchoolDto>> schoolList() throws Exception {
		logger.info("1-------------schoolList-----------------------------" + new Date());
		List<SchoolDto> emps = schoolService.searchAll();
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SchoolDto>>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = "구 별 학교 개수", response = List.class)
	@RequestMapping(value = "/schoolCount", method = RequestMethod.GET)
	public ResponseEntity<List<ChartDto>> schoolCount() throws Exception {
		logger.info("1-------------schoolList-----------------------------" + new Date());
		List<ChartDto> emps = schoolService.getCount();
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ChartDto>>(emps, HttpStatus.OK);
	}
	// ================school end====================

	// ================station start====================
	@ApiOperation(value = "모든 경찰소방서.", response = List.class)
	@RequestMapping(value = "/stationList", method = RequestMethod.GET)
	public ResponseEntity<List<StationDto>> stationList() throws Exception {
		logger.info("1-------------stationList-----------------------------" + new Date());
		List<StationDto> emps = stationService.searchAll();
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<StationDto>>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = "경찰소방서 개수", response = List.class)
	@RequestMapping(value = "/stationCount", method = RequestMethod.GET)
	public ResponseEntity<List<ChartDto>> stationCount() throws Exception {
		logger.info("1-------------stationCount-----------------------------" + new Date());
		List<ChartDto> emps = stationService.getCount();
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ChartDto>>(emps, HttpStatus.OK);
	}
	// ================station end====================

}
