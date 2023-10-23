package com.example.category;

import com.example.category.Service.ISCategoryService;
import com.example.category.entity.Category;
import com.example.category.entity.CategoryRepository;
import com.example.category.entity.SCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class CategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryApplication.class, args);
	}
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	SCategoryRepository sCategoryRepository;
	@Autowired
	ISCategoryService scategoryService;
	@Bean
	ApplicationRunner init (){
    return (args) -> {
		categoryRepository.save(new Category("Computers and Laptops"));

		categoryRepository.save(new Category("Mobile Devices"));
		categoryRepository.save(new Category("Components and Accessories"));
		categoryRepository.save(new Category("Software"));
		categoryRepository.save(new Category("Electronics and Gadgets"));
		categoryRepository.save(new Category("Networking and Connectivity"));
		categoryRepository.save(new Category("Storage and Memory"));
		categoryRepository.save(new Category("Office Equipment"));
		categoryRepository.save(new Category("Gaming"));
		categoryRepository.save(new Category("Home Appliances"));
		categoryRepository.save(new Category("Health and Fitness Tech"));
		categoryRepository.save(new Category("Security Systems"));
		categoryRepository.save(new Category("Industrial and Professional Tech"));
		categoryRepository.save(new Category("Virtual Reality (VR) and Augmented Reality (AR)"));
		categoryRepository.findAll().forEach(System.out::println);
		scategoryService.addSubCategory(1L,"Desktop computers");
		scategoryService.addSubCategory(1L,"Laptop computers");
		scategoryService.addSubCategory(1L,"Workstations");
		categoryRepository.findAll().forEach(System.out::println);
		sCategoryRepository.findAll().forEach(System.out::println);

	};
	}


}
