package com.ashu.boot.services;

import java.util.List;

import com.ashu.boot.dto.CategoryDTO;
import com.ashu.boot.models.Category;


public interface CategoryService {
	List<CategoryDTO> generate();
	CategoryDTO generateOne(Integer id);
	Category add(CategoryDTO categoryDTO);
	void edit(int id ,CategoryDTO categoryDTO);
	void del(int id);

}
