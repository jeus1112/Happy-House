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

import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.help.BoolResult;
import com.ssafy.happyhouse.help.NumberResult;
import com.ssafy.happyhouse.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2021")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// ================user start====================
	@ApiOperation(value = "모든 user.", response = List.class)
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public ResponseEntity<List<UserDto>> userlist() throws Exception {
		logger.info("1-------------userlist-----------------------------" + new Date());
		List<UserDto> emps = userService.searchAll();
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserDto>>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = "login.", response = UserDto.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserDto> login(@RequestBody UserDto user) throws Exception {
		logger.info("1-------------login-----------------------------" + new Date());
		UserDto emps = userService.login(user);
		if (emps == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		logger.info("1-------------login-----------------------------" + emps);
		return new ResponseEntity<UserDto>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = "user select", response = UserDto.class)
	@RequestMapping(value = "/selectuser/{userid}", method = RequestMethod.GET)
	public ResponseEntity<UserDto> selectuser(@PathVariable String userid) throws Exception {
		logger.info("1-------------selectuser-----------------------------" + new Date());
		UserDto emps = userService.select(userid);
		if (emps == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserDto>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = " 회원가입..", response = NumberResult.class)
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<NumberResult> addUser(@RequestBody UserDto userDto) throws Exception {
		logger.info("5-------------addUser-----------------------------" + new Date());
		logger.info("5-------------addUser-----------------------------" + userDto);
		int total = userService.adduser(userDto);
		NumberResult nr = new NumberResult();
		nr.setCount(total);
		nr.setName("addUser");
		nr.setState("succ");
		logger.info("5-------------addUser-------------------------" + total);

		return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 삭제", response = BoolResult.class)
	@RequestMapping(value = "/deleteUser/{userid}", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> deleteUser(@PathVariable String userid) throws Exception {
		logger.info("1-------------deleteUser-----------------------------" + new Date());
		logger.info("1-------------deleteUser-----------------------------" + userid);

		boolean total = userService.deletuser(userid);
		BoolResult nr = new BoolResult();
		nr.setCount(total);
		nr.setName("deleteUser");
		nr.setState("succ");
		if (!total) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@ApiOperation(value = " 회원 수정 ", response = BoolResult.class)
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> updateUser(@RequestBody UserDto user) throws Exception {
		logger.info("1-------------updateUser-----------------------------" + new Date());
		logger.info("1-------------updateUser-----------------------------" + user);
		boolean total = userService.updateuser(user);
		BoolResult nr = new BoolResult();
		nr.setCount(total);
		nr.setName("updateUser");
		nr.setState("succ");
		if (!total) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

}
