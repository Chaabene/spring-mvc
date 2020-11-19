package com.quick.start.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.quick.start.domain.Product;

/**
 * @author Aymen Chaaben
 *
 */
@Component
public class ProductServiceImpl implements ProductService {

	Map<Integer, Product> products;

	public ProductServiceImpl() {
		loadProducts();
	}

	public List<Product> getAllProducts() {
		return new ArrayList<>(products.values());
	}

	public Product getProductById(Integer id) {
		return products.get(id);
	}

	@Override
	public Product saveOrUpdate(Product product) {
		if (product != null) {
			if (product.getId() == null) {
				product.setId(Collections.max(products.keySet()) + 1);
			}
			products.put(product.getId(), product);
			return product;
		} else {
			throw new RuntimeException("Product cannot be null");
		}
	}

	private void loadProducts() {
		products = new HashMap<>();

		Product product1 = new Product();
		product1.setId(1);
		product1.setDescription("product 1");
		product1.setPrice(new BigDecimal("55.25"));
		product1.setImageUrl("https://media.ldlc.com/r1600/ld/products/00/04/85/56/LD0004855639_2.jpg");
		products.put(1, product1);

		Product product2 = new Product();
		product2.setId(2);
		product2.setDescription("product 2");
		product2.setPrice(new BigDecimal("60.25"));
		product2.setImageUrl("https://media.ldlc.com/r1600/ld/products/00/04/85/56/LD0004855639_2.jpg");
		products.put(2, product2);

		Product product3 = new Product();
		product3.setId(3);
		product3.setDescription("product 3");
		product3.setPrice(new BigDecimal("61.25"));
		product3.setImageUrl("https://media.ldlc.com/r1600/ld/products/00/04/85/56/LD0004855639_2.jpg");
		products.put(4, product3);

	}

	@Override
	public void removeProduct(Integer id) {
		products.remove(id);
	}

}
