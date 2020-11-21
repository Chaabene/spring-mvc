package com.quick.start.domain;

import java.math.BigDecimal;

public class Product {
	private Integer id;
	private String description;
	private BigDecimal price;
	private String imageUrl;
	
	
	public Product() {
		super();
	}
	public Product(Integer id, String description, BigDecimal price, String imageUrl) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	
}
