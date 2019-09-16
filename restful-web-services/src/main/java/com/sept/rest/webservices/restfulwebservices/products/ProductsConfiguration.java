//package com.sept.rest.webservices.restfulwebservices.products;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ProductsConfiguration {
//
//	@Bean
//	CommandLineRunner runner(ProductJpaRepository productRepository) {
//
//		return args -> {
//			productRepository.save(new Product("Programmers Guide", 12.00, "blablablablabla"));
//			productRepository.save(new Product("Programmers Guide", 12.00, "blablablablabla"));
//			productRepository.save(new Product("Elephant Book", 13.50, "blablablablabla"));
//			productRepository.save(new Product("Self Taught Programmer", 11.00, "blablablablabla"));
//			productRepository.save(new Product("Computer Science Book", 10.50, "blablablablabla"));
//			productRepository.save(new Product("Beginning Programming Reference for dummies", 13.00, "blablablablabla"));
//			productRepository.save(new Product("Computer Science Distilled", 14.00, "blablablablabla"));
//			productRepository.save(new Product("Computer science principles", 14.00, "blablablablabla"));
//			productRepository.save(new Product("Structure and Interpretation of Computer Programs", 15.00, "blablablablabla"));
//			productRepository.save(new Product("AP Computer Science A", 16.50, "blablablablabla"));
//		};
//	}
//
//}
