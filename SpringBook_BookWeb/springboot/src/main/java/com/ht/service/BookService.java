package com.ht.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.entities.Sach;
import com.ht.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	/// HIEN THI TAT CA SACH
	public List<Sach> findAll() {
//			
		return bookRepository.findAll();
	}

	public List<Sach> findByCategory(long id) {
		return bookRepository.findByCategory(id);

	}

	public Sach findById(long ids) {
		return bookRepository.findByName(ids);
	}
//	Tìm kím

//	public List<String> findBook(String name) {
//		return bookRepository.findBookName(name);
//	}
//
////	choose
//	public List<Sach> findBookByTitle(String tenSach) {
//		return bookRepository.findBookByTitle(tenSach);
//	}

	public List<Sach> findBookByTitle(String tenSach) {
		return bookRepository.findBookByTitle(tenSach);
	}

	public List<String> findBook(String name) {
		return bookRepository.findBookName(name);
	}

	/// THEM MOI SACH
	public void createBook(Sach sach) {
		bookRepository.saveAndFlush(sach);
	}

	/// CHINH SUA SACH
	public void update(Sach sach) {
		Sach book = bookRepository.findByName(sach.getMaSach());
		book.setHinhAnh(sach.getHinhAnh());
		book.setGia(sach.getGia());
		book.setMoTa(sach.getMoTa());
		book.setNgayXuatBan(sach.getNgayXuatBan());
		book.setTenSach(sach.getTenSach());
		book.setTenTacGia(sach.getTenTacGia());
		book.setTheloaisach(sach.getTheloaisach());
		book.setKhuyenMai(sach.getKhuyenMai());
		book.setSoLuong(sach.getSoLuong());
		bookRepository.saveAndFlush(book);
	}

	///// XOA SACH
	public void delete(long id) {
		Sach sach = bookRepository.findByName(id);
		if (sach != null) {
			bookRepository.delete(sach);
		}
	}
}
