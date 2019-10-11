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

import com.ashu.boot.dto.ProductDTO;
import com.ashu.boot.models.Product;
import com.ashu.boot.services.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		return ResponseEntity.ok(productService.generate());
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable int productId) {
		ProductDTO product=productService.generateOne(productId);
		if(product==null)
			return ResponseEntity.notFound().build();
		else
				return ResponseEntity.ok().body(product);
		
	}

	@ResponseBody
	@PostMapping("/products/category/{categoryId}")
	public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO, @PathVariable Integer categoryId) {
		 Product product = productService.add(productDTO,categoryId); 
		 HttpHeaders responseHeaders = new
		 HttpHeaders(); 
		 URI newProductUri = ServletUriComponentsBuilder
				 .fromCurrentRequest().path("/{categoryId}")
				 .buildAndExpand(product.getId()).toUri();
		 responseHeaders.setLocation(newProductUri); 
		 return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
		 

	}

	@PutMapping("/products/{productId}/category/{categoryId}")
	public ResponseEntity<?> editProduct(@PathVariable Integer productId,@PathVariable Integer categoryId, @RequestBody ProductDTO productDTO) {
		productService.edit(productId, productDTO,categoryId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> delProduct(@PathVariable Integer productId){
		productService.del(productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

