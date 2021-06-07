package com.ht.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.ValidationUtils;

import com.ht.entities.User;
import com.ht.service.CustomerValidation;
import com.ht.service.UserService;

@Controller
public class SingupController {
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerValidation customerValidation;

	/** 07/06/2021 */

//	@RequestMapping(value = "/signup1")
//	public ModelAndView signup(Model model) {
//		ModelAndView mav = new ModelAndView("signup");
//		model.addAttribute("user", new User());
//		return mav;
//	}

//	@RequestMapping(value = "/signup", method = RequestMethod.POST)
//	public ModelAndView signup(@ModelAttribute("user") User user, Model model) {
//		ModelAndView mav = new ModelAndView("login");
////		if (result.hasErrors()) {
////			return mav = new ModelAndView("signup");
////		}
//		userService.create(user);
//
//		return mav;
//	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup(Model model) {
		ModelAndView mav = new ModelAndView("signup");
		model.addAttribute("user", new User());
		return mav;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("user") @Valid User user, Errors errors, BindingResult result,
			Model model) {
//		ValidationUtils.invokeValidator(customerValidation, user, errors);

		ModelAndView mav = null;
		if (result.hasErrors()) {
			return mav = new ModelAndView("signup");
		}
		userService.create(user);

		return mav = new ModelAndView("login");
	}

}
