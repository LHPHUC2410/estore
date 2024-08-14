package com.estore.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.estore.dao.OrderDAO;
import com.estore.dao.OrderDetailsDAO;
import com.estore.entity.Customer;
import com.estore.entity.Order;
import com.estore.entity.OrderDetails;
import com.estore.entity.Product;
import com.estore.service.CartService;

@Controller
public class OrderController {
	@Autowired
	HttpSession session;
	@Autowired
	CartService cart;
	@Autowired
	OrderDAO dao;
	@Autowired
	OrderDetailsDAO ddao;
	
	@GetMapping("/order/checkout")
	public String showForm(@ModelAttribute("order") Order order, ModelMap model) {	
		if(cart.isEmpty()) {
			model.addAttribute("message", "Giỏ hàng trống");
			return "user/cart/view";
		}
		Customer user = (Customer) session.getAttribute("user");
		order.setOrderDate(new Date());
		order.setCustomer(user);
		order.setReceiver(user.getFullname());
		order.setAmount(cart.getAmount());
		if(order.getRequireDate() == null) {
			Date currentDate = new Date();
		    
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(currentDate);
		    calendar.add(Calendar.DAY_OF_MONTH, 14);   
		    Date requireDate = calendar.getTime();
		    
		    order.setRequireDate(requireDate);
		}
		return "user/order/checkout";
	}
	@PostMapping("/order/checkout")
	public String purchase(ModelMap model, @Valid @ModelAttribute("order")Order order, BindingResult errors) {
		if(errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa lỗi !!!");
			return "user/order/checkout";
		}
		Collection<Product> list = cart.getItems();
		List<OrderDetails> details = new ArrayList<>();
		for(Product product: list) {
			OrderDetails detail = new OrderDetails();
			detail.setOrder(order);
			detail.setProduct(product);
			detail.setUnitPrice(product.getUnitPrice());
			detail.setQuantity(product.getQuantity());
			detail.setDiscount(product.getDiscount());
			details.add(detail);
		}
		dao.create(order, details);
		cart.clear();
		return "redirect:/order/list";
	}
	
	@GetMapping("/order/list")
	public String list(Model model) {
		Customer user =  (Customer) session.getAttribute("user");
		List<Order> orders = dao.findByUser(user);
		model.addAttribute("orders", orders);
		return "user/order/list";
	}
	@GetMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Order order = dao.findById(id);
		List<OrderDetails> details = ddao.findByOrder(order);
		model.addAttribute("order", order);
		model.addAttribute("details", details);
		return "user/order/detail";
	}
	@GetMapping("/order/items")
	public String items(Model model) {
		Customer user =  (Customer) session.getAttribute("user");
		List<Product> list = dao.findItemsByUser(user);
		model.addAttribute("list", list);
		return "user/product/list";
	}
}
