package com.estore.admin.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.estore.entity.security.Master;

@Transactional
@Controller("adminHomeController")
@RequestMapping("/admin/home")
public class HomeController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String index() {
		return "admin/home/index";
	}
	
	@RequestMapping("login")
	public String login() {
		return "admin/home/login";
	}
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(ModelMap model,
			@RequestParam("id") String id,
			@RequestParam("password") String pw,
			HttpSession httpSession,
			HttpServletResponse response) {
		Session session = factory.getCurrentSession();
		try {
			Master master = (Master) session.get(Master.class, id);
			if(!master.getPassword().equals(pw)){
				model.addAttribute("message", "Invalid password !");
			}
			else{
				model.addAttribute("message", "Login successfully");
				httpSession.setAttribute("master", master);
								
				String url = (String) httpSession.getAttribute("AdminBackUrl");
				if(url != null){
					return "redirect:"+url;
				}
			}
		} catch (Exception e) {
			model.addAttribute("message", "Invalid username !");
		}
		
		return "admin/home/index";
	}

	@RequestMapping("logoff")
	public String logoff(HttpSession httpSession) {
		httpSession.removeAttribute("master");
		return "redirect:/admin/home/login";
	}
	
	@RequestMapping("unauthorized")
	public String unauthorized() {
		return "admin/home/unauthorized";
	}
}
