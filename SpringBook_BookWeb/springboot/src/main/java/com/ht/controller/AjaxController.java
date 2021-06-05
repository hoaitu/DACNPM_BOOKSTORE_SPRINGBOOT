//package com.ht.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ht.service.BookService;
//
//@RestController
//public class AjaxController {
//
//	@Autowired
//	private BookService bookService;
//
//	@RequestMapping(value = "/ajaxSearch", method = RequestMethod.GET)
//	public List<String> getSach(String tenSach) {
//		List<String> getSach = bookService.findBook(tenSach);
//		for (String sach : getSach) {
//			System.out.println(sach + "\n");
//		}
//		return getSach;
//	}
//
//}
