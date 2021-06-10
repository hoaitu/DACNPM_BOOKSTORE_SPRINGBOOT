package com.ht.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ht.entities.User;
import com.ht.repository.UserRepository;

@Service
@Transactional
public class CustomerServices {

	@Autowired
	private UserRepository customerRepo;

	public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
		User customer = customerRepo.findByEmail(email);
		if (customer != null) {
			customer.setResetPasswordToken(token);
			customerRepo.save(customer);
		} else {
			throw new UsernameNotFoundException("Could not find any customer with the email " + email);
		}
	}

	public User getByResetPasswordToken(String token) {
		return customerRepo.findByResetPasswordToken(token);
	}

	public void updatePassword(User customer, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		customer.setPassword(encodedPassword);

		customer.setResetPasswordToken(null);
//		customerRepo.save(customer);
		customerRepo.saveAndFlush(customer);
	}
}