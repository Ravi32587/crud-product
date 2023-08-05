package com.spring.project.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.demo.modal.ProductVO;
import com.spring.project.demo.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ArrayList<ProductVO> fetchAllProducts(){
		return productService.fetchAllProducts();
	}

	@PostMapping("/products")
	public String saveProduct(@RequestBody ProductVO productVO) {
		return productService.saveProduct(productVO);
	}
	
	@GetMapping("products/{id}")
	public Object getProductById(@PathVariable Integer id) {
		ProductVO productVO = productService.getProductById(id);
		if(productVO!=null) {
			return productVO;
		}
		return "No record fount with id "+id;
	}
	@DeleteMapping("products/{id}")
	public String deleteProductById(@PathVariable Integer id) {
		return productService.deleteProductById(id);
	}
	
	@PutMapping("products/{id}")
	public String updateProductById(@PathVariable Integer id,@RequestBody ProductVO productVO) {
		return productService.updateProductById(id, productVO);
	}
	
}
