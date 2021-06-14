package com.ht.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

//import com.ht.config.EncrytedPasswordUtils;

=======
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
>>>>>>> origin/trang2
import com.ht.entities.User;
import com.ht.repository.UserRepository;
/////////TRANG 14/6
@Service
@Transactional
public class UserService {
	@Autowired
<<<<<<< HEAD
	private UserRepository uRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//	Add user in Admin + Add user in Singup : pass must Encode
	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		uRepository.saveAndFlush(user);
	}

	// User use
	public User findByUsernameAndPassword(String username, String password) {
		return uRepository.findByUsernameAndPassword(username, password);
	}

	public List<User> findAll() {
		return uRepository.findAll();
=======
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
>>>>>>> origin/trang2
	}

//////////CAP NHAT TRONG ADMIN
	public void update(User user) {
		User u = uRepository.findByUser((user.getIdUser()));
		u.setUsername(user.getUsername());
		u.setEmail(user.getEmail());
		u.setPassword(passwordEncoder.encode(user.getPassword()));
		u.setRequest(user.getRequest());
		u.setAddress(user.getAddress());
		u.setDateOfBirth(user.getDateOfBirth());
		u.setGender(user.getGender());
		u.setPhone(user.getPhone());
		uRepository.saveAndFlush(u);
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
		User u = uRepository.findByUser(idUser);
		if (u != null) {
			uRepository.delete(u);
		}
	}

	public User findById(Long id) {
		return uRepository.findByUser(id);
	}

<<<<<<< HEAD
//TU: funtion for find userName exits (Mail exits) 13/06/2021
	public User findCustomerByUserName(String userName) {
		return uRepository.findByUserName(userName);
	}

=======
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

>>>>>>> origin/trang2
}
