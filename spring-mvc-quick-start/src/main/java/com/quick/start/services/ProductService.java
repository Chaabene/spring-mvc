package com.quick.start.services;

import java.util.List;

import com.quick.start.domain.Product;

public interface ProductService {
	List<Product> getAllProducts();
	default Product getProductById(Integer id) {
		return null;
	}
	
	Product saveOrUpdate(Product product);
	void removeProduct(Integer id);
}
