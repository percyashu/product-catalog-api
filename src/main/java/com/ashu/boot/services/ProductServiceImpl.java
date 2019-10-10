package com.ashu.boot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashu.boot.dto.ProductDTO;
import com.ashu.boot.models.Category;
import com.ashu.boot.models.Product;
import com.ashu.boot.repository.CategoryRepository;
import com.ashu.boot.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService {
	
	
	private ProductRepository repository;
	private CategoryRepository repositoryCat;
	
	@Autowired
	public ProductServiceImpl(ProductRepository repository ,CategoryRepository repositoryCat) {
	this.repository=repository;	
	this.repositoryCat=repositoryCat;
	}
	
	@Override
	public List<ProductDTO> generate() {
			 
		Iterable<Product> products= repository.findAll();
		List<ProductDTO> productDTOS = new ArrayList<ProductDTO>();
		for(Product product : products) {
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
	public Product add(ProductDTO productDTO,int id) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setQuantity(productDTO.getQuantity());
		product.setPrice(productDTO.getPrice());
		productDTO.setCategory(new Category(id,""));
		product.setCategory(productDTO.getCategory());
		repository.save(product);
		return product;
	}

	@Override
	public ProductDTO generateOne(Integer id) {
		Product product= repository.findById(id).get();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setQuantity(product.getQuantity());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setCategory(product.getCategory());
		return productDTO;
	}

	@Override
	public void edit(int id ,ProductDTO productDTO,int catId) {
		Iterable<Product> products=repository.findAll();
		Category cat=repositoryCat.findById(catId).get();
		for(Product product : products) {
			if(id==product.getId()&&cat==product.getCategory()) {
				product.setName(productDTO.getName());
				product.setQuantity(productDTO.getQuantity());
				product.setPrice(productDTO.getPrice());
				repository.save(product);
				
			}
		}
	}

	@Override
	public void del(int id) {
		repository.deleteById(id);
		
	}
}
