package com.ht.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ht.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//	TU: Used for Check Email User have Exits in DB in Page Regester(dang ky)
	@Query(value = "SELECT c FROM User c where  c.username = :username")
	public User findByUserName(@Param("username") String username);

	/*
	 * @Query(value = "SELECT u FROM User u WHERE u.email = :email") public abstract
	 * List<User> findByEmail(@Param("email") String email);
	 */

	/////////// LAY USER THEO EMAIL DE HIEN THI TRONG TRANG THONG TIN TAI KHOAN
	@Query(value = "SELECT u FROM User u WHERE u.email = :email")
<<<<<<< HEAD
	public abstract List<User> findByEmail2(@Param("email") String email);

//	Tu moi them 09/06; 
//	 Using reset Pass
	public User findByEmail(String email);

//	TU: Using reset Pass
	public User findByResetPasswordToken(String token);
//	------------------

	User findByUsernameAndPassword(String username, String password);

//User name : is email
	User findByUsername(String username);

	@Query(value = "Select u From User u where u.idUser =:ids")
	public abstract User findByUser(@Param("ids") long id);
=======
	public abstract User findByEmail(@Param("email") String email);

	User findByUsernameAndPassword(String username, String password);

/////////////TIM THEO ID THUC HIEN CHUC NANG SUA, XOA
	@Query(value = "Select u From User u where u.idUser =:ids")
	public abstract User findByUser(@Param("ids") long id);

	User findByUsername(String username);
>>>>>>> origin/trang2

}
