//package com.ht.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ht.entities.Sach;
//import com.ht.service.BookService;
//import com.ht.service.CategoryService;
//import com.ht.service.PageService;
//
//@Controller
//public class PagingController {
//	@Autowired
//	private PageService pageService;
//	@Autowired
//	private CategoryService category;
//	@Autowired
//	private BookService bookService;
//
//	@RequestMapping("/shops")
//	@ResponseBody
//	public List<Sach> viewPhanTrang2(Model model, @RequestParam int currentPage) {
//		Page<Sach> page = pageService.listAll(currentPage);
////		long totalItems = page.getTotalElements();
////		int totalPage = page.getTotalPages();
//		List<Sach> listSach = page.getContent();
////		model.addAttribute("totalItems", totalItems);
////		model.addAttribute("currentPage", currentPage);
////		model.addAttribute("totalPage", totalPage);
////		model.addAttribute("listAllBooks", listSach);
//		model.addAttribute("listcategory", category.findAll());
//		return listSach;
//	}
//
//	@RequestMapping("/shop")
//	public String viewPhanTrang(ModelMap model) {
//		int currentPage = 1;
//		Page<Sach> page = pageService.listAll(currentPage);
//		List<Sach> listSach = page.getContent();
//		int totalPage = page.getTotalPages();
//		model.addAttribute("totalPage", totalPage);
////		model.addAttribute("listbook", bookService.findAll());
////		model.addAttribute("listSach", listSach);
//		model.addAttribute("listbook", listSach);
//		model.addAttribute("listcategory", category.findAll());
//		return "shop";
//	}
//}
