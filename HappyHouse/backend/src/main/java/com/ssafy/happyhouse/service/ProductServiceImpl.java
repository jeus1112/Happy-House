package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.ProductDao;
import com.ssafy.happyhouse.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductDao productDao;

	@Override
	@Transactional
	public int insert(ProductDto productDto) throws Exception {
		productDao.insert(productDto);
		return productDao.afterAdd();
	}

	@Override
	@Transactional
	public List<ProductDto> searchAll() {
		return productDao.searchAll();
	}

	@Override
	@Transactional
	public ProductDto search(int no) {
		return productDao.search(no);
	}

	@Override
	@Transactional
	public boolean update(ProductDto productDto) throws SQLException {
		return productDao.update(productDto);
	}

	@Override
	@Transactional
	public boolean delete(int no) throws SQLException {
		return productDao.delete(no);
	}

	@Override
	public List<ProductDto> searchTitle(String title) throws SQLException {
		return productDao.searchTitle(title);
	}

	@Override
	public List<ProductDto> findArticleById(String userid) throws Exception {
		return productDao.findArticleById(userid);
	}

}
