package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.ProductDto;

public interface ProductService {

	int insert(ProductDto productDto) throws SQLException, Exception;

	List<ProductDto> searchAll();

	ProductDto search(int no);

	boolean update(ProductDto productDto) throws SQLException;

	boolean delete(int no) throws SQLException;

	List<ProductDto> searchTitle(String title) throws SQLException;

	public List<ProductDto> findArticleById(String userid) throws Exception;
}