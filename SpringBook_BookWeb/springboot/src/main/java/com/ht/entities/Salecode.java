package com.ht.entities;
// Generated Dec 20, 2020, 10:04:20 AM by Hibernate Tools 5.1.10.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Salecode generated by hbm2java
 */
@Entity
@Table(name = "salecode", catalog = "book_dacnpm")
public class Salecode implements java.io.Serializable {

	private Integer idSale;
	private String codeSale;
	private Integer khuyenMai;

	public Salecode() {
	}

	public Salecode(String codeSale, Integer khuyenMai) {
		this.codeSale = codeSale;
		this.khuyenMai = khuyenMai;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idSale", unique = true, nullable = false)
	public Integer getIdSale() {
		return this.idSale;
	}

	public void setIdSale(Integer idSale) {
		this.idSale = idSale;
	}

	@Column(name = "codeSale")
	public String getCodeSale() {
		return this.codeSale;
	}

	public void setCodeSale(String codeSale) {
		this.codeSale = codeSale;
	}

	@Column(name = "khuyenMai")
	public Integer getKhuyenMai() {
		return this.khuyenMai;
	}

	public void setKhuyenMai(Integer khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

}
