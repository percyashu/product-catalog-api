package com.ashu.boot.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.ashu.boot.models.Product;

@Repository
public interface ProductRepository extends JpaRepositoryImplementation<Product, Integer> {

}
