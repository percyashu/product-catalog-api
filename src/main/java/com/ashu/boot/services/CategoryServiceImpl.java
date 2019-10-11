package com.ashu.boot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashu.boot.dto.CategoryDTO;
import com.ashu.boot.exception.CategoryNotFoundException;
import com.ashu.boot.models.Category;
import com.ashu.boot.repository.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	
	private CategoryRepository repository;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository repository) {
	this.repository=repository;	
	}
	
	@Override
	public List<CategoryDTO> generate() {
			 
		Iterable<Category> categories= repository.findAll();
		
		List<CategoryDTO> categoryDTOS = new ArrayList<CategoryDTO>();
		for(Category category : categories) {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(category.getId());
			categoryDTO.setName(category.getName());
			categoryDTOS.add(categoryDTO);
			
		}
			 return categoryDTOS;
	}

	@Override
	public Category add(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setName(categoryDTO.getName());
		repository.save(category);
		return category;
	}

	@Override
	public CategoryDTO generateOne(Integer id) {
		Boolean bool =repository.existsById(id);
		if(bool!=false) {
			Category category =repository.findById(id).get();
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(category.getId());
			categoryDTO.setName(category.getName());
			return categoryDTO;
		}
		else {
			throw new CategoryNotFoundException("CategoryId - "+id+" doesn't exist");
		}
		
	}

	@Override
	public void edit(int id ,CategoryDTO categoryDTO) {
		Boolean bool =repository.existsById(id);
		if(bool!=false) {
			Category category =repository.findById(id).get();
			category.setName(categoryDTO.getName());
			repository.save(category);	
		}
		else {
			throw new CategoryNotFoundException("CategoryId - "+id+" doesn't exist");
		}
	}

	@Override
	public void del(int id) {
		Boolean bool =repository.existsById(id);
		if(bool!=false) {
			repository.deleteById(id);
		}
		else {
			throw new CategoryNotFoundException("CategoryId - "+id+" doesn't exist");
		}
		
		
	}
}
