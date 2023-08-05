package com.spring.project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.spring.project.demo.entity.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
