package com.spring.project.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.demo.entity.Category;
import com.spring.project.demo.entity.Product;
import com.spring.project.demo.modal.CategoryVO;
import com.spring.project.demo.modal.ProductVO;
import com.spring.project.demo.repository.CategoryRepo;
import com.spring.project.demo.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	public ArrayList<ProductVO> fetchAllProducts(){
		List<Product> products = productRepo.findAll();
		ArrayList<ProductVO> productVOs=new ArrayList<>();
		for(Product product:products) {
			ProductVO productVO=new ProductVO();
			productVO.setId(product.getId());
			productVO.setName(product.getName());
			CategoryVO categoryVO=new CategoryVO();
			Category category = product.getCategory();
			categoryVO.setId(category.getId());
			categoryVO.setName(category.getName());
			categoryVO.setType(category.getType());
			productVO.setCategory(categoryVO);
			productVOs.add(productVO);
		}
		return productVOs;
		
	}
	
	public String saveProduct(ProductVO productVO) {
		Optional<Category> category = categoryRepo.findById(productVO.getCategory().getId());
		if(category.isPresent()) {
			Product product=new Product();
			product.setCategory(category.get());
			product.setName(productVO.getName());
			productRepo.save(product);
			return "Product details save successfully";
		}
		return "No able to product category because invalid category Id";	
	}
	public ProductVO getProductById(Integer id) {
		Optional<Product> product = productRepo.findById(id);
		if(product.isPresent()) {
			Product product2 = product.get();
			ProductVO productVO=new ProductVO();
			productVO.setId(id);
			productVO.setName(product2.getName());
			Category category = product2.getCategory();
			CategoryVO categoryVO=new CategoryVO();
			categoryVO.setId(category.getId());
			categoryVO.setName(category.getName());
			categoryVO.setType(category.getType());
			productVO.setCategory(categoryVO);
			return productVO;
		}
		return null;	
	}
	
	public String deleteProductById(Integer id) {
		if(productRepo.existsById(id)){
			productRepo.deleteById(id);
			return "Record deleted";
		}
		return "No record found";
	}
	public String updateCategoryById(Integer id,CategoryVO categoryVO) {
		if(categoryRepo.existsById(id)) {
			Category category = new Category();
			category.setId(id);
			category.setName(categoryVO.getName());
			category.setType(categoryVO.getType());
			categoryRepo.save(category);
			return "Record updated successfully";
		}
		return "No record found with id="+id;
	}
	public String updateProductById(Integer id,ProductVO productVO) {
		Optional<Category> category = categoryRepo.findById(productVO.getCategory().getId());
		if(!category.isPresent()) {
			return "No able to update product because invalid category Id";	
		}
		if(productRepo.existsById(id)){
			Product product=new Product();
			product.setId(id);
			product.setName(productVO.getName());
			product.setCategory(category.get());
			productRepo.save(product);
			return "Record updated";
		}
		return "No record found";
	}
	
}
