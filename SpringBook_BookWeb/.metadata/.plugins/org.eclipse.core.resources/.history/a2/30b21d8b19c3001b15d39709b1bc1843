package com.bookstore.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.User;
import com.bookstore.repository.UserRepository;

@Repository
@Transactional
public class UserDAO {
	@Autowired
	private UserRepository uRepository;

	public User findByEmail(String email) {
		List<User> listUser = uRepository.findByEmail(email);
		if (listUser != null && listUser.size() > 0) {
			return listUser.get(0);
		}
		return null;

	}
}
