package com.ashu.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.ashu.boot.repository")
public class ProductCatalogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApiApplication.class, args);
	}

}
