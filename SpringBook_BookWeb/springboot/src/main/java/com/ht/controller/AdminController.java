package com.ht.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ht.entities.Sach;
import com.ht.entities.Theloaisach;
import com.ht.entities.User;
import com.ht.service.BookService;
import com.ht.service.CategoryService;
import com.ht.service.ReceiptItemService;
import com.ht.service.ReceiptService;
import com.ht.service.UserService;

@Controller
public class AdminController {
	@Autowired
	private BookService book;
	@Autowired
	private CategoryService category;
	@Autowired
	private UserService userSevice;
	@Autowired
	private ReceiptService receiptService;
	@Autowired
	private ReceiptItemService receiptItemService;

//////////////HIEN THI DANH SACH SAN PHAM
	@RequestMapping("/admin")
	public ModelAndView listProduct(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/index");
		model.addAttribute("listbook", book.findAll());
		return mav;
	}

//////THEM MOI SAN PHAM
	@RequestMapping("/admin/productAdd")
	public ModelAndView addProduct(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/product-add");
		model.addAttribute("books", new Sach());
		model.addAttribute("listbook", book.findAll());
		return mav;
	}

///THEM MOI SAN PHAM
	@RequestMapping(value = "/admin/productAdd", method = RequestMethod.POST)
	public ModelAndView addProduct(ModelMap model, @ModelAttribute("books") Sach sach) {
		ModelAndView mav = new ModelAndView("admin/index");
		book.createBook(sach);
		model.addAttribute("listbook", book.findAll());
		return mav;
	}

////CHINH SUA SAN PHAM ID
	@RequestMapping(value = "/admin/productEdit/{ids}", method = RequestMethod.GET)
	public ModelAndView productEdit(ModelMap model, @PathVariable(value = "ids") long id) {
		ModelAndView mav = new ModelAndView("admin/product-edit");
		model.addAttribute("books", book.findById(id));
		return mav;
	}

////////CHINH SUA SAN PHAM ID
	@RequestMapping(value = "/admin/productEdit/{ids}", method = RequestMethod.POST)
	public ModelAndView productEdit(ModelMap model, @ModelAttribute("books") Sach sach) {
		ModelAndView mav = new ModelAndView("admin/index");
		book.update(sach);
		model.addAttribute("listbook", book.findAll());
		return mav;
	}

////XOA SAN PHAM THEO ID
	@RequestMapping(value = "/admin/productDelete/{ids}", method = RequestMethod.GET)
	public ModelAndView productDelete(ModelMap model, @PathVariable(value = "ids") long id) {
		ModelAndView mav = new ModelAndView("admin/index");
		book.delete(id);
		model.addAttribute("listbook", book.findAll());
		return mav;
	}

	@RequestMapping("/admin/productDetail")
	public ModelAndView adminProductDetail(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/product-detail");

		return mav;
	}

/////////////HIEN THI DANH SACH USER
	@RequestMapping("/admin/listCustomer")
	public ModelAndView listCustomer(Model model) {
		ModelAndView mav = new ModelAndView("admin/customer");
		model.addAttribute("listAllUser", userSevice.findAll());
		return mav;
	}

/// XOA TAI KHOAN THEO IDUSER
	@RequestMapping("/admin/deleteUser/{idUser}")
	public ModelAndView deleteUser(ModelMap model, @PathVariable("idUser") long idUser) {
		ModelAndView mav = new ModelAndView("admin/customer");
		userSevice.delete(idUser);
		model.addAttribute("listAllUser", userSevice.findAll());
		return mav;
	}

// THEM MOI MOT TAI KHOAN
	@RequestMapping(value = "/admin/addUser", method = RequestMethod.GET)
	public ModelAndView addUser(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/customer-add");
		model.addAttribute("user", new User());
		model.addAttribute("listAllUser", userSevice.findAll());
		return mav;
	}

// THEM MOI MOT TAI KHOAN
	@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(ModelMap model, @ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("admin/customer");
		userSevice.create(user);
		model.addAttribute("listAllUser", userSevice.findAll());
		return mav;
	}

// CHINH SUA TAI KHOAN
	@RequestMapping(value = "/admin/editCustomter/{idUser}", method = RequestMethod.GET)
	public ModelAndView editCustomter(ModelMap model, @PathVariable(value = "idUser") long id) {
		ModelAndView mav = new ModelAndView("admin/customer-edit");
		model.addAttribute("user", userSevice.findById(id));
		return mav;
	}

	@RequestMapping(value = "/admin/editCustomter/{idUser}", method = RequestMethod.POST)
	public ModelAndView editCustomter(ModelMap model, @ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("admin/customer");
		userSevice.update(user);
		model.addAttribute("listAllUser", userSevice.findAll());
		return mav;
	}

///////////HIEN THI DANH SACH THE LOAI
	@RequestMapping("/admin/listProductType")
	public ModelAndView listProductType(Model model) {
		ModelAndView mav = new ModelAndView("admin/productType");
		model.addAttribute("listcategory", category.findAll());
		return mav;
	}

//// SAN PHAM THEO LOAI
	@RequestMapping(value = "/admin/listProductType/{id}", method = RequestMethod.GET)
	public ModelAndView productType(Model model, @PathVariable(value = "id") int id) {
		ModelAndView mav = new ModelAndView("admin/index");
		model.addAttribute("listbook", book.findByCategory(id));
		return mav;
	}

/// XOA LOAI SAN PHAM THEO MA THE LOAI
	@RequestMapping("/admin/deleteType/{maTheLoai}")
	public ModelAndView deleteType(ModelMap model, @PathVariable("maTheLoai") long maTheLoai) {
		ModelAndView mav = new ModelAndView("admin/productType");
		category.delete(maTheLoai);
		model.addAttribute("listcategory", category.findAll());
		return mav;
	}

/// THEM LOAI SAN PHAM MOI
	@RequestMapping(value = "/admin/addType", method = RequestMethod.GET)
	public ModelAndView addType(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/productType-add");
		model.addAttribute("category", new Theloaisach());
		model.addAttribute("listcategory", category.findAll());
		return mav;
	}

//// THEM LOAI SAN PHAM MOI
	@RequestMapping(value = "/admin/addType", method = RequestMethod.POST)
	public ModelAndView addType(ModelMap model, @ModelAttribute("category") Theloaisach theloaisach) {
		ModelAndView mav = new ModelAndView("admin/productType");
		category.create(theloaisach);
		model.addAttribute("listcategory", category.findAll());
		return mav;
	}

// CHINH SUA LOAI SAN PHAM
	@RequestMapping(value = "/admin/editType/{maTheLoai}", method = RequestMethod.GET)
	public ModelAndView editType(ModelMap model, @PathVariable(value = "maTheLoai") long id) {
		ModelAndView mav = new ModelAndView("admin/productType-edit");
		model.addAttribute("category", category.findById(id));
		return mav;
	}

// CHINH SUA LOAI SAN PHAM
	@RequestMapping(value = "/admin/editType/{maTheLoai}", method = RequestMethod.POST)
	public ModelAndView editType(ModelMap model, @ModelAttribute("category") Theloaisach theloaisach) {
		ModelAndView mav = new ModelAndView("admin/productType");
		category.update(theloaisach);
		model.addAttribute("listcategory", category.findAll());
		return mav;
	}

/////DANH SACH DON HANG
	@RequestMapping("/admin/listOrder")
	public ModelAndView listOrder(Model model) {
		ModelAndView mav = new ModelAndView("admin/cart");
		model.addAttribute("listOrders", receiptItemService.showAll());
		return mav;
	}
	/// CHI TIET DON HANG

	@RequestMapping("/admin/orderDetail/{id}")
	public ModelAndView orderDetail(Model model, @PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView("admin/cart-detail");
		model.addAttribute("listdetail", receiptItemService.findID(id));
		model.addAttribute("order", receiptService.findid(id));
		return mav;
	}

	/// XOA DON HANG
	@RequestMapping("/admin/deleteOrder/{id}")
	public String deleteOrder(ModelMap model, @PathVariable("id") long id) {
		receiptService.delete(id);
		return "redirect:/admin/listOrder";
	}

	/// CHINH SUA DON HANG
	@RequestMapping(value = "/admin/editOrder", method = RequestMethod.GET)
	public ModelAndView editOrder(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/cart-edit");
		return mav;
	}

}
