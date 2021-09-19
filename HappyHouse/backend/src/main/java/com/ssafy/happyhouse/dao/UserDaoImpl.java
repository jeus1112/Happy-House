package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao {

	private String ns = "com.ssafy.happyhouse.dao.UserMapper.";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserDto login(UserDto user) {
		return sqlSession.selectOne(ns + "login", user);
	}

	@Override
	public UserDto select(String userid) {
		return sqlSession.selectOne(ns + "search", userid);
	}

	@Override
	public List<UserDto> searchAll() {
		return sqlSession.selectList(ns + "searchAll");
	}

	@Override
	public void adduser(UserDto user) throws SQLException {
		sqlSession.insert(ns + "adduser", user);

	}

	@Override
	public boolean updateuser(UserDto user) throws SQLException {
		sqlSession.update(ns + "updateuser", user);
		return true;
	}

	@Override
	public boolean deletuser(String userid) throws SQLException {
		sqlSession.delete(ns + "deletuser", userid);
		return true;
	}

	@Override
	public int afterAdd() throws Exception {
		return sqlSession.selectOne(ns + "afterAdd");
	}

}
