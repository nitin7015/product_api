package com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category ,Integer>
{
			Category findByCategoryId(Integer categoryId);
}
