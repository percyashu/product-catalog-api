package com.ashu.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashu.boot.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
