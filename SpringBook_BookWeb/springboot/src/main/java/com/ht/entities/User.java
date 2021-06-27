package com.ht.entities;
// Generated Dec 20, 2020, 10:04:20 AM by Hibernate Tools 5.1.10.Final

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

//import com.bookstore.validator.Emails;
//import com.bookstore.validator.Phone;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "book_dacnpm")
public class User implements java.io.Serializable {

	private Long idUser;
	private String email;
	private String password;
	private Integer level = 1;
	private Integer active = 1;
	private String code = "ROLE_USER";
	private String request;
	private String username;
	private String phone;
	private String gender;
	private Date dateOfBirth;
	private String address;
	private String resetPasswordToken;
	private Set<Bill> bills = new HashSet<Bill>(0);
	private Set<Bill> bills_1 = new HashSet<Bill>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Wishlist> wishlists = new HashSet<Wishlist>(0);
	private Set<UsersRoles> usersRoleses = new HashSet<UsersRoles>(0);

	public User() {
	}

	public User(String email) {
		this.email = email;
	}

	public User(String email, String password, String request, String username, String phone, String gender,
			String address, Date dateOfBirth) {
		this.email = email;
		this.password = password;
		this.username = username;
		this.phone = phone;
		this.gender = gender;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.request = request;
	}

	public User(String email, String password, Integer level, Integer active, String request, Set<Bill> bills,
			Set<Bill> bills_1, Set<Comment> comments, Set<Wishlist> wishlists) {
		this.email = email;
		this.password = password;
		this.level = level;
		this.active = active;
		this.request = request;
		this.bills = bills;
		this.bills_1 = bills_1;
		this.comments = comments;
		this.wishlists = wishlists;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idUser", unique = true, nullable = false)
	public Long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

//	@NotBlank(message = "must not be null")
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Size(min = 6, message = "Password must have atleast 6 characters")
	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	@Emails(message = "must not be null")
	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "phone")
//	@Phone(message = "Phone Number is invalid")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

//	@NotBlank(message = "must not be null")
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

//	@NotBlank(message = "must not be null")
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "level")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "active")
	public Integer getActive() {
		return this.active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

//	@NotBlank(message = "must not be null")
	@Column(name = "request")
	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	@Column(name = "reset_password_token")
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Bill> getBills() {
		return this.bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Bill> getBills_1() {
		return this.bills_1;
	}

	public void setBills_1(Set<Bill> bills_1) {
		this.bills_1 = bills_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Wishlist> getWishlists() {
		return this.wishlists;
	}

	public void setWishlists(Set<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "users")
	public Set<UsersRoles> getUsersRoleses() {
		return this.usersRoleses;
	}

	public void setUsersRoleses(final Set<UsersRoles> usersRoleses) {
		this.usersRoleses = usersRoleses;
	}

	@Transient
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (UsersRoles usersRoles : this.usersRoleses) {
			authorities.add(new SimpleGrantedAuthority(usersRoles.getRole().getName()));
		}
		return authorities;
	}

//	@appUserTransient
//	public List<GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
////		getRole ==getCode
//		authorities.add(new SimpleGrantedAuthority(this.getCode()));
//		return authorities;
//	}

}
