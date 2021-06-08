package com.ht.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.entities.Receipt;
import com.ht.repository.ReceiptRepository;

@Service
public class ReceiptService {
	@Autowired
	private ReceiptRepository reRepository;

	public void create(Receipt receipt) {
		reRepository.saveAndFlush(receipt);
	}

	public Receipt findid(long id) {
		return reRepository.findByID(id);
	}

	public void delete(long id) {
		Receipt r = reRepository.findByID(id);
		if (r != null) {
			reRepository.delete(r);
		}

	}
}
