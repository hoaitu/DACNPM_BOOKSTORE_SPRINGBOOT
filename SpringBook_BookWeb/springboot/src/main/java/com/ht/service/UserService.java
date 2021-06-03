//package com.ht.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.ht.entities.User;
//import com.ht.repository.UserRepository;
//
//@Service
//public class UserService implements UserDetailsService {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	public void saveCustomer(User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		user.setRole("user");
//		user.setUsersRoleses(usersRoleses);
//		userRepository.save(user);
//	}
//
//	public User findCustomerByUserName(String userName) {
//		return userRepository.findByUserName(userName);
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findByUserName(username);
//		if (user == null)
//			throw new UsernameNotFoundException(username);
//		return new UserDetail(user);
//	}
//
//	public User getCustomerByEmail(String email) {
//		return userRepository.findByEmail(email);
//
//	}
//
//	public void createNewCustomerAfterOAuthLoginSuccess(String email, String name, AuthenticationProvider provider) {
//		User customer = new User();
//
//		customer.setUsername(email);
//		customer.setEmail(email);
//		customer.setName(name);
////		customer.setProvider(provider);
//		customer.setRole("user");
//		userRepository.save(customer);
//
//	}
//
//	public void updateCustomerAfterOAuthLoginSuccess(User customer, String name, AuthenticationProvider provider) {
//		customer.setName(name);
//		customer.setProvider(provider);
//		// customer.setRole("user");
//		userRepository.save(customer);
//
//	}
//
//}
