package com.assignment.controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.constants.ResponseEntity;
import com.assignment.dto.CategoryDTO;
import com.assignment.dto.ProductDTO;
import com.assignment.model.Category;
import com.assignment.model.Product;
import com.assignment.service.ProductService;

@RestController
@RequestMapping("/")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Value("${image-dir}")
	private String mainFolderPath;
	
	@GetMapping("/getAllCategories")
	public ResponseEntity getAllCategories(){
		
		List<CategoryDTO> allCategories = productService.getAllCategories();
		ResponseEntity response = new ResponseEntity();
		
		if(allCategories!=null) {
			response.setMessage("Success ");
			response.setStatus(200);
			response.setObject(allCategories);
		}else
		{
			response.setMessage("No Categories found ");
			response.setStatus(200);
			response.setObject("");
		}
		return response;
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity addProduct(ProductDTO product,@RequestParam("file") MultipartFile file) {
			
			ResponseEntity response = new ResponseEntity();
			if(file!=null && !file.isEmpty()) {
				ProductDTO pro =productService.addProduct(product, file);
				response.setMessage("Success!! Product added successfully ");
				response.setStatus(200);
				response.setObject(pro);
				response.setPath(Paths.get(File.separator+mainFolderPath+File.separator).toString());
			}
			else {
				response.setMessage("Fail!! Something went wrong");
				response.setStatus(300);
				response.setObject("");
			}
			return response;
	}
	
	@GetMapping("/searchProduct")
	public ResponseEntity searchProduct(String param) {
		List<ProductDTO> allProducts = productService.searchProduct(param);
		ResponseEntity response = new ResponseEntity();
		if(allProducts!=null) {
			response.setMessage("Success ");
			response.setStatus(200);
			response.setObject(allProducts);
			response.setPath(Paths.get(File.separator+mainFolderPath+File.separator).toString());
		}else
		{
			response.setMessage("Products not found ");
			response.setStatus(300);
			response.setObject("");
		}
		return response;
	}
}
