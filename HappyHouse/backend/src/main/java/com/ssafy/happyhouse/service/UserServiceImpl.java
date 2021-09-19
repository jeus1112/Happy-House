package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.UserDao;
import com.ssafy.happyhouse.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public UserDto login(UserDto user) {
		return userDao.login(user);
	}

	@Override
	public UserDto select(String userid) {
		return userDao.select(userid);
	}

	@Override
	public List<UserDto> searchAll() {
		return userDao.searchAll();
	}

	@Override
	public int adduser(UserDto user) throws Exception {
		userDao.adduser(user);
		return userDao.afterAdd();
	}

	@Override
	public boolean updateuser(UserDto user) throws SQLException {
		return userDao.updateuser(user);
	}

	@Override
	public boolean deletuser(String userid) throws SQLException {
		return userDao.deletuser(userid);
	}

}
