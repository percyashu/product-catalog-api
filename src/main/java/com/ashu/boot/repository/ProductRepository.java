package com.ashu.boot.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashu.boot.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findByIdAndCategoryId(int Id ,int categoryId);
}
