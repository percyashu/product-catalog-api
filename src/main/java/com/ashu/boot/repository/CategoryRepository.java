package com.ashu.boot.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.ashu.boot.models.Category;

public interface CategoryRepository extends JpaRepositoryImplementation<Category, Integer> {

}
