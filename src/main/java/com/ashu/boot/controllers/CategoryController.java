package com.ashu.boot.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ashu.boot.dto.CategoryDTO;
import com.ashu.boot.models.Category;
import com.ashu.boot.services.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CategoryController {

	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/category")
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		return ResponseEntity.ok(categoryService.generate());
	}

	@GetMapping("category/{categoryId}")
	public ResponseEntity<CategoryDTO> getUser( @PathVariable int categoryId) {
		CategoryDTO category=categoryService.generateOne(categoryId);
		if(category==null)
			return ResponseEntity.notFound().build();
		else
				return ResponseEntity.ok().body(category);
		
	}

	@ResponseBody
	@PostMapping("/category")
	public ResponseEntity<?> saveUser(@RequestBody CategoryDTO categoryDTO) {
		Category category = categoryService.add(categoryDTO);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newCategoryUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{categoryId}")
				.buildAndExpand(category.getId()).toUri();
		responseHeaders.setLocation(newCategoryUri);
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/category/{categoryId}")
	public ResponseEntity<?> editUser(@PathVariable Integer categoryId, @RequestBody CategoryDTO categoryDTO) {
		categoryService.edit(categoryId, categoryDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<?> delUser(@PathVariable int categoryId ){
		categoryService.del(categoryId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
