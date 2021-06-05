package com.ht.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

//	@GetMapping(value = { "/", "/index" })
//	public String homePage(Model model) {
//		return "index2";
//	}

//	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
//	public String index(Model model) {
//
////        model.addAttribute("message", message);
//
//		return "index";
//	}

	@RequestMapping("/")
	public ModelAndView home(ModelMap model) {
		ModelAndView mav = new ModelAndView("index");

		return mav;
	}

	@RequestMapping("/shop")
	public ModelAndView shop(ModelMap model) {
		ModelAndView mav = new ModelAndView("shop");

		model.addAttribute("listbook", book.findAll());
		model.addAttribute("listcategory", category.findAll());

		return mav;
	}

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

//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public ModelAndView logout(ModelMap model) {
//		ModelAndView mav = new ModelAndView("index");
//
//		return mav;
//	}

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String loginPage(Model model) {
// 
//        return "login";
//    }

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

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView cart(ModelMap model) {
		ModelAndView mav = new ModelAndView("cart");

		return mav;
	}

	@RequestMapping("/checkout")
	public ModelAndView checkout(ModelMap model) {
		ModelAndView mav = new ModelAndView("checkout");

		return mav;
	}

	/**
	 * 2/06/2021 : thêm trang 404
	 */

//	@RequestMapping("/403")
//	public ModelAndView err404(ModelMap model) {
//		ModelAndView mav = new ModelAndView("404");
//
//		return mav;
//	}
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

//	-----------------------------------------------------------------------

//	@RequestMapping("/admin")
//	public ModelAndView admin(ModelMap model) {
//		ModelAndView mav = new ModelAndView("admin/index");
//
//		return mav;
//	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage(Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("admin/index");
		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);

		return mav;
	}

//	-------------------------------------------

	@RequestMapping("/admin/add")
	public ModelAndView adminAdd(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/product-add");

		return mav;
	}

	@RequestMapping("/admin/edit")
	public ModelAndView adminEdit(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/product-edit");

		return mav;
	}

	@RequestMapping("/admin/productDetail")
	public ModelAndView adminProductDetail(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/product-detail");

		return mav;
	}

	@RequestMapping("/admin/productPay")
	public ModelAndView adminProductPay(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/product-payment");

		return mav;
	}

	///////////////////

	@RequestMapping("/admin/manaUser")
	public ModelAndView adminUser(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/list-user");

		return mav;
	}

//	[[[[[[[[[[ơ

	@RequestMapping("/test")
	public ModelAndView testPG(ModelMap model) {
		ModelAndView mav = new ModelAndView("test");

		return mav;
	}

}
