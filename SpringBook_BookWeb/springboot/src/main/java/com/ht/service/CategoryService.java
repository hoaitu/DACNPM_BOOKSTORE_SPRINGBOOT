package com.ht.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.entities.Theloaisach;
import com.ht.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Theloaisach> findAll() {
		return categoryRepository.findAll();
	}
}
