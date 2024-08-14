package com.estore.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estore.bean.MailInfo;
import com.estore.dao.CategoryDAO;
import com.estore.dao.ProductDAO;
import com.estore.entity.Category;
import com.estore.entity.Customer;
import com.estore.entity.Product;
import com.estore.service.MailService;

@Controller
public class HomeController {
	@Autowired
	CategoryDAO cdao;
	@Autowired
	ProductDAO pdao;
	
	@Autowired
	MailService mailer;
	
	@RequestMapping("/home/index")
	public String index(ModelMap model) {
	//	List<Category> list = cdao.getRandoms(); 
	//	model.addAttribute("slideshow_cates", list);
		List<Product> list = pdao.findAll();
		model.addAttribute("list", list);
		return "user/product/list";
	}
	@RequestMapping("/home/about")
	public String about() {
		return "user/home/about";
	}
	@RequestMapping("/home/contact")
	public String contact() {
		return "user/home/contact";
	}
	@GetMapping("/home/feedback")
	public String feedback(Model model) {
		MailInfo info = new MailInfo();
		model.addAttribute("form", info);
		return "user/home/feedback";
	}
	@PostMapping("/home/feedback")
	public String postMethodName(Model model, @Valid @ModelAttribute("form")MailInfo info, BindingResult errors) throws IllegalStateException, IOException, MessagingException {
		info.setTo("n21dccn126@student.ptithcm.edu.vn");
		if(errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa lỗi !!!");
			return "user/home/feedback";
		}
		mailer.send(info);
		model.addAttribute("message", "Your feedback was sent to support team");
		return "user/home/feedback";
	}
	
	@RequestMapping("/home/faq")
	public String faq() {
		return "user/home/faq";
	}
}
