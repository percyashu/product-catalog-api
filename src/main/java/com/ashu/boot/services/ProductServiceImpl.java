package com.ashu.boot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashu.boot.dto.ProductDTO;
import com.ashu.boot.exception.ProductNotFoundException;
import com.ashu.boot.models.Category;
import com.ashu.boot.models.Product;
import com.ashu.boot.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository repository;

	@Autowired
	public ProductServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ProductDTO> generate() {

		Iterable<Product> products = repository.findAll();
		List<ProductDTO> productDTOS = new ArrayList<ProductDTO>();
		for (Product product : products) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setQuantity(product.getQuantity());
			productDTO.setPrice(product.getPrice());
			productDTO.setCategory(product.getCategory());
			productDTOS.add(productDTO);

		}
		return productDTOS;
	}

	@Override
	public Product add(ProductDTO productDTO, int id) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setQuantity(productDTO.getQuantity());
		product.setPrice(productDTO.getPrice());
		productDTO.setCategory(new Category(id, ""));
		product.setCategory(productDTO.getCategory());
		repository.save(product);
		return product;
	}

	@Override
	public ProductDTO generateOne(Integer id) {
		Boolean bool = repository.existsById(id);
		if(bool!=false) {
			Product product = repository.findById(id).get();
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(product.getId());
			productDTO.setQuantity(product.getQuantity());
			productDTO.setName(product.getName());
			productDTO.setPrice(product.getPrice());
			productDTO.setCategory(product.getCategory());
			return productDTO;
			
		}else {
			throw new ProductNotFoundException("ProductId - "+id+" doesn't exist" );

		}
		
	}

	@Override
	public void edit(int Id, ProductDTO productDTO, int categoryId) {
		Boolean bool = repository.findByIdAndCategoryId(Id, categoryId).isPresent();
		if (bool != false) {
			Product product=repository.findByIdAndCategoryId(Id, categoryId).get();
			product.setName(productDTO.getName());
			product.setQuantity(productDTO.getQuantity());
			product.setPrice(productDTO.getPrice());
			repository.save(product);
		}
		else {
			throw new ProductNotFoundException("ProductId - "+Id+" or Category Id -"+categoryId+ " doesn't exist" );
		}

	}

	@Override
	public void del(int id) {
		Boolean bool = repository.existsById(id);
		if (bool != false) {
			repository.deleteById(id);
		}
		else {
			throw new ProductNotFoundException("ProductId - "+id+"  doesn't exist" );
		}
	

	}
}
