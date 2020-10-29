package com.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.dto.CategoryDTO;
import com.assignment.dto.ProductDTO;
import com.assignment.model.Category;
import com.assignment.model.Product;
import com.assignment.repository.CategoryRepository;
import com.assignment.repository.ProductRepository;
import com.assignment.utilities.UtilFunctions;

@Service
public class ProductService {
	@Autowired
	UtilFunctions utilFunctions;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	public List<CategoryDTO> getAllCategories(){
		
		List<Category> allCategories = categoryRepo.findAll();
		List<CategoryDTO>alldtos = new ArrayList<CategoryDTO>();
		if(allCategories!=null) {
			allCategories.forEach(category->alldtos.add(new CategoryDTO(category)));
		}
		return alldtos;
	}
	
	public CategoryDTO getCategoryById(Integer Id) {
		Category category = categoryRepo.findByCategoryId(Id);
		return new CategoryDTO(category);
	}
	
	public ProductDTO addProduct(ProductDTO product,MultipartFile file) {
			
			String uploadedImage = utilFunctions.uploadFileAndGetPath(file);
			product.setImageUrl(uploadedImage);
			Product pro =null;
			
			if(product.getProductId()!=null) {
				pro= productRepo.findByProductId(product.getProductId());
			}
			else {
				pro = new Product();
			}
			
			pro.setCategory(categoryRepo.findByCategoryId(product.getCategoryId()));
			pro.setColor(product.getColor());
			pro.setPrice(product.getPrice());
			pro.setProductName(product.getProductName());
			if(product.getImageUrl()!=null) {
				pro.setImageURL(product.getImageUrl());	
			}
			productRepo.saveAndFlush(pro);
			
			return new ProductDTO(pro);
		
	}
	

	public List<ProductDTO> searchProduct(String param){
		List<ProductDTO> allDto = new ArrayList<ProductDTO>();
		List<Product> allProducts=null;
		if(param!=null) {
			if(UtilFunctions.isNumeric(param)) {
				Category category = categoryRepo.findByCategoryId(Integer.parseInt(param));
				if(category!=null) {
					allProducts =  productRepo.findByCategory(category);
				}
			}
			else
			{
				allProducts=productRepo.searchProduct(param);
			}
		}
		else {
			allProducts = productRepo.findAll();
		}
		allProducts.forEach(product->allDto.add(new ProductDTO(product)));
		return allDto;
	}
}
