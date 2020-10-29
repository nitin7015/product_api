package com.assignment.dto;

import com.assignment.model.Category;

import lombok.Data;

@Data
public class CategoryDTO {
	private Integer categoryId;
	
	private String categoryName;
	
	public CategoryDTO() {}
	
	public CategoryDTO(Category category) {
		this.categoryId = category.getCategoryId();
		this.categoryName=category.getCategoryName();
	}
}
