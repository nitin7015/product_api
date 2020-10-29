package com.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assignment.model.Category;
import com.assignment.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>
{
	@Query("SELECT p FROM Product p WHERE CONCAT(p.productName, p.color,p.price) LIKE %?1%")
	List<Product> searchProduct(String param);
	
	Product findByProductId(Integer productId);
	
	List<Product> findByCategory(Category category);
}
