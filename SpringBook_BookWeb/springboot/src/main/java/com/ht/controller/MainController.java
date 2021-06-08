package com.ht.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ht.WebUtils;
import com.ht.entities.Sach;
import com.ht.service.BookService;
import com.ht.service.CategoryService;
import com.ht.service.PageService;

//

import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class MainController {

	@Autowired
	private BookService book;
	@Autowired
	private CategoryService category;
	@Autowired
	private PageService pageService;

	@RequestMapping("/")
	public ModelAndView home(ModelMap model) {
		ModelAndView mav = new ModelAndView("index");

		return mav;
	}

//	@RequestMapping("/shop")
//	public ModelAndView shop(ModelMap model) {
//		ModelAndView mav = new ModelAndView("shop");
//
//		model.addAttribute("listbook", book.findAll());
//		model.addAttribute("listcategory", category.findAll());
//
//		return mav;
//	}
//	------------------------------------------------------------

//	@RequestMapping("/shops")
////	@ResponseBody
//	public ModelAndView viewPhanTrang2(Model model, @RequestParam int currentPage) {
//		ModelAndView mav = new ModelAndView("shop");
//
//		Page<Sach> page = pageService.listAll(currentPage);
////		long totalItems = page.getTotalElements();
////		int totalPage = page.getTotalPages();
//		List<Sach> listSach = page.getContent();
//		model.addAttribute("listbook", listSach);
////		model.addAttribute("totalItems", totalItems);
////		model.addAttribute("currentPage", currentPage);
////		model.addAttribute("totalPage", totalPage);
////		model.addAttribute("listAllBooks", listSach);
////		model.addAttribute("listcategory", category.findAll());
//		return mav;
//	}

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
////		model.addAttribute("listcategory", category.findAll());
//		return "shop";
//	}

//	@RequestMapping("/shop")
//	public ModelAndView viewPhanTrang(ModelMap model) {
//		ModelAndView mav = new ModelAndView("shop");
//		int currentPage = 1;
//		Page<Sach> page = pageService.listAll(currentPage);
//		List<Sach> listSach = page.getContent();
//		int totalPage = page.getTotalPages();
//		model.addAttribute("totalPage", totalPage);
////		model.addAttribute("listbook", bookService.findAll());
////		model.addAttribute("listSach", listSach);
//		model.addAttribute("listbook", listSach);
////		model.addAttribute("listcategory", category.findAll());
//		return mav;
//	}

//	------------------------------------------------------------

//	Tú mới cmment 06/06
	@RequestMapping(value = "/shop/{id}", method = RequestMethod.GET)
	public ModelAndView shop(Model model, @PathVariable(value = "id") int id) {
		ModelAndView mav = new ModelAndView("shop");
		model.addAttribute("listbook", book.findByCategory(id));
		model.addAttribute("listcategory", category.findAll());

		return mav;
	}

//	

	@RequestMapping(value = "/search")
	public @ResponseBody List<Sach> ajaxSearch(HttpServletRequest req, HttpServletResponse res) {
		List<Sach> getSach = book.findBookByTitle(req.getParameter("tenSach"));
		for (Sach sach : getSach) {
			System.out.println(sach + "\n");
		}
		return getSach;
	}

	@RequestMapping(value = "/ajaxSearch", method = RequestMethod.GET)
	public List<String> getSach(String tenSach) {
		List<String> getSach = book.findBook(tenSach);
		for (String sach : getSach) {
			System.out.println(sach + "\n");
		}
		return getSach;
	}

//	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {
		ModelAndView mav = new ModelAndView("login");

		return mav;
	}

	@RequestMapping("/signup")
	public ModelAndView signup(ModelMap model) {
		ModelAndView mav = new ModelAndView("signup");

		return mav;
	}

	@RequestMapping("/single-product/{ids}")
	public ModelAndView single_product(Model model, @PathVariable(value = "ids") int ids) {
		ModelAndView mav = new ModelAndView("single-product");
		mav.addObject("books", book.findById(ids));
//		model.addAttribute("listBook", bookService.findSameCategory());
		return mav;
	}

	@RequestMapping("/wishlist")
	public ModelAndView wishlist(ModelMap model) {
		ModelAndView mav = new ModelAndView("wishlist");

		return mav;
	}

	@RequestMapping("/my-account")
	public ModelAndView account(ModelMap model) {
		ModelAndView mav = new ModelAndView("my-account");

		return mav;
	}

	@RequestMapping("/shopping_cart")
	public ModelAndView cart(ModelMap model) {
		ModelAndView mav = new ModelAndView("cart");

		return mav;
	}

	/**
	 * 2/06/2021 : thêm trang 404
	 */

//để test
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public ModelAndView userInfo(Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("userInfoPage");
		// After user login successfully.
		String userName = principal.getName();

		System.out.println("User Name: " + userName);

		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);

		return mav;
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public ModelAndView logoutSuccessfulPage(Model model) {
		ModelAndView mav = new ModelAndView("logoutSuccessfulPage");
		model.addAttribute("title", "Logout");
		return mav;
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accessDenied(Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("404");
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);

			model.addAttribute("userInfo", userInfo);

			String message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);

		}

		return mav;
	}

}
