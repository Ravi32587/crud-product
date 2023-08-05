package com.spring.project.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.demo.entity.Category;
import com.spring.project.demo.modal.CategoryVO;
import com.spring.project.demo.service.CategoryService;


@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public ArrayList<CategoryVO> getAllRecords(){
		return categoryService.fetchAllCategoryRecord();
	}

	@PostMapping("/categories")
	public String saveCategory(@RequestBody CategoryVO categoryVO) {
		return categoryService.saveCategory(categoryVO);
	}
	
	@GetMapping("/categories/{id}")
	public Object getCategoryById(@PathVariable Integer id) {
		CategoryVO categoryVO = categoryService.fetchCategoryById(id);
		if(categoryVO!=null) {
			return categoryVO;
		}
		return "No record found by "+id;
	}
	
	@PutMapping("/categories/{id}")
	public String updateCategory(@PathVariable Integer id,@RequestBody CategoryVO categoryVO) {
		return categoryService.updateCategoryById(id, categoryVO);
	}
	
	@DeleteMapping("/categories/{id}")
	public String deleteCategoryById(@PathVariable Integer id) {
		return categoryService.deleteCategoryById(id);
	}
}
