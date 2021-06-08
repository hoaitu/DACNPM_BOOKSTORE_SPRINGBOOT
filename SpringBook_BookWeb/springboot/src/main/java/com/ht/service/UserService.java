package com.ht.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import com.ht.config.EncrytedPasswordUtils;
import org.springframework.stereotype.Service;

import com.ht.entities.User;
import com.ht.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
//	private EncrytedPasswordUtils passwordEncoder;

	public void create(User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		uRepository.saveAndFlush(user);
//		uRepository.save(user);
	}

//	public List<User> findAll() {
//		return uRepository.findAll();
//	}

	// User dùng
	public User findByUsernameAndPassword(String username, String password) {
		return uRepository.findByUsernameAndPassword(username, password);
	}

//	public boolean saveUser(User user) {
//		String password = user.getPassword();
//		return uRepository.save(user) != null;
//	}

	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

//	public void create(User user) {
//		userRepository.saveAndFlush(user);
//	}

	public void update(User user) {
		User u = userRepository.findByUser((user.getIdUser()));
		u.setUsername(user.getUsername());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setRequest(user.getRequest());
		u.setAddress(user.getAddress());
		u.setDateOfBirth(user.getDateOfBirth());
		u.setGender(user.getGender());
		u.setPhone(user.getPhone());
		userRepository.saveAndFlush(u);
	}

	public void delete(Long idUser) {
		User u = userRepository.findByUser(idUser);
		if (u != null) {
			userRepository.delete(u);
		}
	}

	public User findById(Long id) {
		return userRepository.findByUser(id);
	}
}
