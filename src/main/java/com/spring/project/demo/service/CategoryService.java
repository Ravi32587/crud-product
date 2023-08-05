package com.spring.project.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.demo.entity.Category;
import com.spring.project.demo.modal.CategoryVO;
import com.spring.project.demo.repository.CategoryRepo;


@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	public ArrayList<CategoryVO> fetchAllCategoryRecord(){
		List<Category> allCategories = categoryRepo.findAll();
		ArrayList<CategoryVO> allCategoryVOs=new ArrayList<CategoryVO>();
		for(Category category:allCategories) {
			CategoryVO categoryVO=new CategoryVO();
			categoryVO.setId(category.getId());
			categoryVO.setName(category.getName());
			categoryVO.setType(category.getType());
			allCategoryVOs.add(categoryVO);
		}
		return allCategoryVOs;
	}

	public String saveCategory(CategoryVO categoryVO) {
		Category category=new Category();
		category.setName(categoryVO.getName());
		category.setType(categoryVO.getType());
		categoryRepo.save(category);
		return "Category saved successfullt";
	}
	
	public CategoryVO fetchCategoryById(Integer id) {
		Optional<Category> findById = categoryRepo.findById(id);
		if(findById.isPresent()) {
			Category category = findById.get();
			CategoryVO categoryVO=new CategoryVO();
			categoryVO.setId(id);
			categoryVO.setName(category.getName());
			categoryVO.setType(category.getType());
			return categoryVO;
		}
		return null;
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
	
	public String deleteCategoryById(Integer id) {
		if(categoryRepo.existsById(id)) {
			categoryRepo.deleteById(id);
			return "Category record deleted successfully";
		}
		return "No record found with id="+id;
	}
	

}
