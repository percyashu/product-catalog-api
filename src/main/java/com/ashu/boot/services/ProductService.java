package com.ashu.boot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ashu.boot.dto.ProductDTO;
import com.ashu.boot.models.Product;


@Service
public interface ProductService {
	
	List<ProductDTO> generate();
	ProductDTO generateOne(Integer id);
	Product add(ProductDTO productDTO,int categoryId);
	void edit(int id ,ProductDTO productDTO, int productId);
	void del(int id);
	
	
	

}
