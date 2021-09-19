package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.ProductDto;
import com.ssafy.happyhouse.dto.SearchCondition;

public interface ProductDao {
	void insert(ProductDto productDto) throws SQLException;

	List<ProductDto> searchAll();

	ProductDto search(int no);

	boolean update(ProductDto productDto) throws SQLException;

	boolean delete(int no) throws SQLException;

	int getTotalSearchCount(SearchCondition searchcondition);

	List<ProductDto> searchTitle(String title) throws SQLException;

	public int afterAdd() throws Exception;

	public List<ProductDto> findArticleById(String userid) throws Exception;
}
