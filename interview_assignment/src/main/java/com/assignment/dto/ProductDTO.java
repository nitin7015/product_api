package com.assignment.dto;

import com.assignment.model.Product;

import lombok.Data;

@Data
public class ProductDTO {
		
	private Integer productId;
	private String productName;
	private String imageUrl;
	private String color;
	private Double price;
	private Integer categoryId;
	
	public ProductDTO() {}
	
	public ProductDTO(Product product) {
		this.categoryId= product.getCategory().getCategoryId();
		this.color=product.getColor();
		this.imageUrl=product.getImageURL();
		this.price=product.getPrice();
		this.productId=product.getProductId();
		this.productName=product.getProductName();
	}
	
}
