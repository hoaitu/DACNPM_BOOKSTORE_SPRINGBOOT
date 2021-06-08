package com.ht.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ht.entities.Cart;
import com.ht.entities.Receipt;
import com.ht.entities.ReceiptItem;
import com.ht.entities.Sach;
import com.ht.service.BookService;
import com.ht.service.ReceiptItemService;
import com.ht.service.ReceiptService;

@Controller
@RequestMapping(value = "cart")
public class ControllerCart {

	@Autowired
	private BookService bookService;
	@Autowired
	private ReceiptService receiptService;
	@Autowired
	private ReceiptItemService receiptItemService;

	@RequestMapping(value = "add/{productId}", method = RequestMethod.GET)
	public String viewAdd(ModelMap mm, HttpSession session, @PathVariable("productId") long productId) {
		HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		Sach product = bookService.findById(productId);
		if (product != null) {
			if (cartItems.containsKey(productId)) {
				Cart item = cartItems.get(productId);
				item.setProduct(product);
				item.setQuantity(item.getQuantity() + 1);
				cartItems.put(productId, item);
			} else {
				Cart item = new Cart();
				item.setProduct(product);
				item.setQuantity(1);
				cartItems.put(productId, item);
			}
		}
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "redirect:/shop";
	}

	@RequestMapping(value = "sub/{productId}", method = RequestMethod.GET)
	public String viewUpdate(ModelMap mm, HttpSession session, @PathVariable("productId") long productId) {
		HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		Sach product = bookService.findById(productId);
		if (product != null) {
			if (cartItems.containsKey(productId)) {
				Cart item = cartItems.get(productId);
				item.setProduct(product);
				item.setQuantity(item.getQuantity() + 1);
				cartItems.put(productId, item);
			} else {
				Cart item = new Cart();
				item.setProduct(product);
				item.setQuantity(1);
				cartItems.put(productId, item);
			}
		}
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "redirect:/shopping_cart";
	}

	@RequestMapping(value = "subs/{productId}", method = RequestMethod.GET)
	public String viewUpdates(ModelMap mm, HttpSession session, @PathVariable("productId") long productId) {
		HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		Sach product = bookService.findById(productId);
		if (product != null) {
			if (cartItems.containsKey(productId)) {
				Cart item = cartItems.get(productId);
				item.setProduct(product);
				if (item.getQuantity() > 1) {
					item.setQuantity(item.getQuantity() - 1);
				}
				cartItems.put(productId, item);
			} else {
				Cart item = new Cart();
				item.setProduct(product);
				item.setQuantity(1);
				cartItems.put(productId, item);
			}
		}
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "redirect:/shopping_cart";
	}

	@RequestMapping(value = "remove/{productId}", method = RequestMethod.GET)
	public String viewRemove(ModelMap mm, HttpSession session, @PathVariable("productId") long productId) {
		HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		if (cartItems.containsKey(productId)) {
			cartItems.remove(productId);
		}
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "redirect:/shopping_cart";
	}

	@RequestMapping(value = "removeAll", method = RequestMethod.GET)
	public String viewRemoveAll(ModelMap mm, HttpSession session) {
		HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");

		session.removeAttribute("myCartItems");
		session.setAttribute("myCartNum", 0);
		session.setAttribute("myCartTotal", 0);
		return "redirect:/shopping_cart";
	}

	public double totalPrice(HashMap<Long, Cart> cartItems) {
		int count = 0;
		for (Map.Entry<Long, Cart> list : cartItems.entrySet()) {
			count += list.getValue().getProduct().getGia() * list.getValue().getQuantity();
		}
		return count;
	}

	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public ModelAndView viewCheckout(ModelMap mm) {
		ModelAndView mav = new ModelAndView("checkout");
		mm.addAttribute("receipt", new Receipt());
		return mav;
	}

	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public String viewCheckout(ModelMap mm, HttpSession session, @ModelAttribute("receipt") Receipt receipt) {
		HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		receipt.setReceiptDate(new Timestamp(new Date().getTime()));
//		receipt.setReceiptDate(new Timestamp(new Date().getTime()));
		receipt.setReceiptStatus(true);
		receipt.setTotal(totalPrice(cartItems));
		receiptService.create(receipt);
		for (Map.Entry<Long, Cart> entry : cartItems.entrySet()) {
			ReceiptItem receiptItem = new ReceiptItem();
			receiptItem.setReceipt(receipt);
			receiptItem.setProduct(entry.getValue().getProduct());
			receiptItem.setReceiptItemPrice(entry.getValue().getProduct().getGia());
			receiptItem.setReceiptItemSale(entry.getValue().getProduct().getKhuyenMai());
			receiptItem.setReceiptItemQuantity(entry.getValue().getQuantity());
			receiptItem.setReceiptItemStatus(true);
			receiptItemService.create(receiptItem);
		}
		cartItems = new HashMap<>();
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", 0);
		session.setAttribute("myCartNum", 0);
		return "success";
	}
}
