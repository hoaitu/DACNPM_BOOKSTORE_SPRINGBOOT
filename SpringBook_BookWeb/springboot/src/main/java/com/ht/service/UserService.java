package com.ht.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ht.entities.User;
import com.ht.repository.UserRepository;
/////////TRANG 14/6
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

////////////HIEN THI DANH SACH USER
	public List<User> findAll() {
		return userRepository.findAll();
	}

///////////THEM TAI KHOAN
	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.saveAndFlush(user);
	}

//////////CAP NHAT TRONG ADMIN
	public void update(User user) {
		User u = userRepository.findByUser((user.getIdUser()));
		u.setUsername(user.getUsername());
		u.setEmail(user.getEmail());
		u.setPassword(passwordEncoder.encode(user.getPassword()));
		u.setRequest(user.getRequest());
		u.setAddress(user.getAddress());
		u.setDateOfBirth(user.getDateOfBirth());
		u.setGender(user.getGender());
		u.setPhone(user.getPhone());
		userRepository.saveAndFlush(u);
	}

////////////CAP NHAT TRONG TRANG MY-ACCOUT.HTML
	public void updates(User user) {
		User u = userRepository.findByEmail(user.getEmail());
		u.setUsername(user.getUsername());
		u.setEmail(user.getEmail());
		u.setPassword(passwordEncoder.encode(user.getPassword()));
		u.setRequest(user.getRequest());
		u.setAddress(user.getAddress());
		u.setDateOfBirth(user.getDateOfBirth());
		u.setGender(user.getGender());
		u.setPhone(user.getPhone());
		userRepository.saveAndFlush(u);
	}

////////XOA TAI KHOAN
	public void delete(Long idUser) {
		User u = userRepository.findByUser(idUser);
		if (u != null) {
			userRepository.delete(u);
		}
	}

	public User findById(Long id) {
		return userRepository.findByUser(id);
	}

	public List<User> findAlls() {
		return userRepository.findAll();
	}

	// User dùng
	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

//	public boolean saveUser(User user) {
//		String password = user.getPassword();
//		return uRepository.save(user) != null;
//	}

}
