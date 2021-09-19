package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.UserDto;

public interface UserDao {

	UserDto login(UserDto user);

	UserDto select(String userid);

	List<UserDto> searchAll();

	void adduser(UserDto user) throws SQLException;

	boolean updateuser(UserDto user) throws SQLException;

	boolean deletuser(String userid) throws SQLException;

	public int afterAdd() throws Exception;
}
