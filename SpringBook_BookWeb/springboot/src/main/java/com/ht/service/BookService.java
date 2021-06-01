package com.ht.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.entities.Sach;
import com.ht.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	/// HIEN THI TAT CA SACH
	public List<Sach> findAll() {
//			
		return bookRepository.findAll();
	}

	public List<Sach> findByCategory(long id) {
		return bookRepository.findByCategory(id);

	}

	public Sach findById(long ids) {
		return bookRepository.findByName(ids);
	}

}
