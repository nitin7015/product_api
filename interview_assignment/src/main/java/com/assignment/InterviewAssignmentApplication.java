package com.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.assignment.model.Category;
import com.assignment.repository.CategoryRepository;

@SpringBootApplication
public class InterviewAssignmentApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(InterviewAssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		if(categoryRepo.findAll()==null || categoryRepo.findAll().isEmpty()) {
			Category category1 = new Category("Laptops & Mobiles");
			Category category2 = new Category("Men's Wear");
			Category category3 = new Category("Home & Kitchen Appliances");
			Category category4 = new Category("Books");
			
			categoryRepo.save(category1);
			categoryRepo.save(category2);
			categoryRepo.save(category3);
			categoryRepo.save(category4);
		}
		
	}

}
