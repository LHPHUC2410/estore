package com.estore.controller;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.estore.bean.MailInfo;
import com.estore.dao.CustomerDAO;
import com.estore.dao.MasterDAO;
import com.estore.entity.Customer;
import com.estore.entity.security.Master;
import com.estore.service.CookieService;
import com.estore.service.MailService;

@Controller
public class AccountController {
	@Autowired
	MasterDAO masterDao;
	@Autowired
	CustomerDAO dao;

	@Autowired
	SessionFactory factory;

	@Autowired
	HttpSession session;

	@Autowired
	CookieService cookie;

	@Autowired
	ServletContext app;

	@Autowired
	MailService mailer;

	@Autowired
	HttpServletRequest request;

	@GetMapping("/account/login")
	public String login(Model model) {
		Cookie ckid = cookie.read("userid");
		Cookie ckpw = cookie.read("pass");
		
		Cookie ckMasterId = cookie.read("masterid");
		Cookie ckMasterPass = cookie.read("masterpass");
		if (ckid != null) {
			String uid = ckid.getValue();
			String pwd = ckpw.getValue();
			model.addAttribute("uid", uid);
			model.addAttribute("pwd", pwd);
		}
		
		if(ckMasterId != null) {
			String uid = ckMasterId.getValue();
			String pwd = ckMasterPass.getValue();
			model.addAttribute("uid", uid);
			model.addAttribute("pwd", pwd);
		}
		return "user/account/login";
	}

	@PostMapping("/account/login")
	public String login(Model model, @RequestParam("id") String id, @RequestParam("pw") String pw,
			@RequestParam(value = "rm", defaultValue = "false") boolean rm) {
		Customer user = new Customer();
		Master master = masterDao.findById(id);
		if (master != null)
			user = null;
		else
			user = dao.findById(id);
		if (user == null && master == null)
			model.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu !");
		else if (master != null && user == null) {

			try {

				if (!master.getPassword().equals(pw)) {
					model.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu !");
				} else {
					model.addAttribute("message", "Đăng nhập thành công !");
					session.setAttribute("master", master);

					/*
					 * String url = (String) session.getAttribute("AdminBackUrl"); if (url != null)
					 * { return "redirect:" + url; }
					 */
					if (rm == true) {
						cookie.create("masterid", master.getId(), 30);
						cookie.create("masterpass", master.getPassword(), 30);
					} else {
						cookie.delete("masterid");
						cookie.delete("masterpass");
					}
					
					return "admin/home/index";
				}
			} catch (Exception e) {
				model.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu !");
			}
		} 
		else if (user != null && master == null) {

			if (!pw.equals(user.getPassword())) {
				model.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu !");
			} else if (!user.getActivated()) {
				model.addAttribute("message", "Tài khoản của bạn hết hiệu lực!");
			} else { // Đăng nhập thành công
				model.addAttribute("message", "Đăng nhập thành công !");
				session.setAttribute("user", user);
				// Ghi nhớ tài khoản bằng cookie
				if (rm == true) {
					cookie.create("userid", user.getId(), 30);
					cookie.create("pass", user.getPassword(), 30);
				} else {
					cookie.delete("userid");
					cookie.delete("pass");
				}

				return "redirect:/home/index";
			}
		}
		return "user/account/login";
	}

	@RequestMapping("/account/logoff")
	public String logoff(Model model) {
		session.removeAttribute("user");
		session.removeAttribute("master");
		return "user/account/login";
	}

	@GetMapping("/account/register")

	public String register(Model model) {
		Customer user = new Customer();
		model.addAttribute("form", user);
		return "user/account/register";
	}

	@PostMapping("/account/register")
	public String register(Model model, @ModelAttribute("form") Customer user,
			@RequestParam("photo_file") MultipartFile file)
			throws IllegalStateException, IOException, MessagingException {
		if (file.isEmpty()) {
			user.setPhoto("user.png");
		} else {
			String dir = app.getRealPath("/static/images/customers");
			File f = new File(dir, file.getOriginalFilename());
			file.transferTo(f);
			user.setPhoto(f.getName());
		}
		user.setActivated(false);

		dao.create(user);
		model.addAttribute("message", "Register successfully");

		String from = "dungtranba0512@gmail.com";
		String to = user.getEmail();
		String subject = "Welcome";
		String url = request.getRequestURL().toString().replace("register", "activate/" + user.getId());
		String body = "Click <a href='" + url + "'>Activate</a>";
		MailInfo mail = new MailInfo(from, to, subject, body);
		mailer.send(mail);

		return "user/account/register";
	}

	@GetMapping("/account/activate/{id}")
	public String activate(Model model, @PathVariable("id") String id) {
		Customer user = dao.findById(id);
		user.setActivated(true);
		dao.update(user);

		return "redirect:/account/login";
	}

	@GetMapping("/account/forgot")
	public String forgot(Model model) {
		return "user/account/forgot";
	}

	@PostMapping("/account/forgot")
	public String forgot(Model model, @RequestParam("id") String id, @RequestParam("email") String email)
			throws MessagingException {
		Customer user = dao.findById(id);
		if (user == null) {
			model.addAttribute("message", "Invalid username");
		} else if (!email.equals(user.getEmail())) {
			model.addAttribute("message", "Invalid email address");
		} else {
			String from = "dungtranba0512@gmail.com";
			String to = user.getEmail();
			String subject = "Forgot password";
			String body = "Your password is " + user.getPassword();
			MailInfo mail = new MailInfo(from, to, subject, body);
			mailer.send(mail);
			model.addAttribute("message", "Your password was sent to your inbox mail");
		}

		return "redirect:/account/login";
	}

	@GetMapping("/account/change")
	public String change(Model model) {
		return "user/account/change";
	}

	@PostMapping("/account/change")
	public String change(Model model, @RequestParam("id") String id, @RequestParam("pw") String pw,
			@RequestParam("pw1") String pw1, @RequestParam("pw2") String pw2) {
		if (!pw1.equals(pw2)) {
			model.addAttribute("message", "Confirm password is not match!");
		} else {
			Customer user = dao.findById(id);
			if (user == null) {
				model.addAttribute("message", "Invalid username");
			} else if (!pw.equals(user.getPassword())) {
				model.addAttribute("message", "Invalid password");
			} else {
				user.setPassword(pw1);
				dao.update(user);
				model.addAttribute("message", "Change password successfully");
			}
		}
		return "redirect:/account/login";
	}

	@GetMapping("/account/edit")
	public String edit(Model model) {
		Customer user = (Customer) session.getAttribute("user");
		model.addAttribute("form", user);
		return "user/account/edit";
	}

	@PostMapping("/account/edit")
	public String edit(Model model, @ModelAttribute("form") Customer user,
			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException {
		if (!file.isEmpty()) {
			String dir = app.getRealPath("/static/images/customers");
			File f = new File(dir, file.getOriginalFilename());
			file.transferTo(f);
			user.setPhoto(f.getName());
		}
		dao.update(user);
		session.setAttribute("user", user);
		model.addAttribute("message", "Update account successfully");
		return "user/account/edit";
	}

}
