package com.estore.controller;

import java.io.Console;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estore.dao.ProductDAO;
import com.estore.entity.Product;
import com.estore.service.CartService;

@Controller
public class ShoppingCartController {
	@Autowired
	CartService cart;
	
	@Autowired
	ProductDAO pdao;
	
	@ResponseBody
	@RequestMapping("/cart/update/{id}/{qty}")
	public Object[] updateQuantity(@PathVariable("id") Integer id, @PathVariable("qty") Integer qty) {
	    cart.update(id, qty);
	    Object[] info = {cart.getCount(), cart.getAmount()};
	    return info;
	}
	@ResponseBody
	@RequestMapping("/cart/add/{id}")
	public Object[] add(@PathVariable("id") Integer id){
		cart.add(id);
		Object[] info = {cart.getCount(), cart.getAmount()};
		return info;
	}
	
	@RequestMapping("/cart/view")
	public String view() {
		return "user/cart/view";
	}
	
	@ResponseBody
	@RequestMapping("/cart/clear")
	public void clear() {
		cart.clear();
	}
	
	@ResponseBody
	@RequestMapping("/cart/remove/{id}")
	public Object[] remove(@PathVariable("id") Integer id){
		cart.remove(id);
		Object[] info = {cart.getCount(), cart.getAmount()};
		return info;
	}
}
