package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.UserDto;

public interface UserService {

	UserDto login(UserDto user);

	UserDto select(String userid);

	List<UserDto> searchAll();

	int adduser(UserDto user) throws SQLException, Exception;

	boolean updateuser(UserDto user) throws SQLException;

	boolean deletuser(String userid) throws SQLException;

}
