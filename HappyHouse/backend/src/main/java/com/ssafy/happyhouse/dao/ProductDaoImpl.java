package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.ProductDto;
import com.ssafy.happyhouse.dto.SearchCondition;

@Repository
public class ProductDaoImpl implements ProductDao {

	private String ns = "com.ssafy.happyhouse.dao.ProductMapper.";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(ProductDto productDto) throws SQLException {
		sqlSession.insert(ns + "insert", productDto);
	}

	@Override
	public List<ProductDto> searchAll() {
		return sqlSession.selectList(ns + "searchAll");
	}

	@Override
	public ProductDto search(int no) {
		return sqlSession.selectOne(ns + "search", no);
	}

	@Override
	public boolean update(ProductDto productDto) throws SQLException {
		sqlSession.update(ns + "update", productDto);
		return true;
	}

	@Override
	public boolean delete(int no) throws SQLException {
		sqlSession.delete(ns + "delete", no);
		return true;
	}

	@Override
	public int getTotalSearchCount(SearchCondition searchcondition) {
		return sqlSession.selectOne(ns + "getTotalSearchCount", searchcondition);
	}

	@Override
	public List<ProductDto> searchTitle(String title) throws SQLException {
		return sqlSession.selectList(ns + "searchTitle", title);
	}

	@Override
	public int afterAdd() throws Exception {
		return sqlSession.selectOne(ns + "afterAdd");
	}

	@Override
	public List<ProductDto> findArticleById(String userid) throws Exception {
		return sqlSession.selectList(ns + "findArticleById", userid);
	}

}
