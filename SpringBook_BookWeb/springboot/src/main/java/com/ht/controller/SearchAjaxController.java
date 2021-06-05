//package com.ht.controller;
//
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.ht.entities.Sach;
//import com.ht.service.BookService;
//
//@Controller
//public class SearchAjaxController {
//	@Autowired
//	private BookService bookService;
//
//	@RequestMapping(value = "/search")
//	public @ResponseBody List<Sach> ajaxSearch(HttpServletRequest req, HttpServletResponse res) {
//		List<Sach> getSach = bookService.findBookByTitle(req.getParameter("tenSach"));
//		for (Sach sach : getSach) {
//			System.out.println(sach + "\n");
//		}
//		return getSach;
//	}
//
//}
