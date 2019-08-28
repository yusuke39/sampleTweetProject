package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;
import com.example.demo.form.UserForm;
import com.example.demo.service.UserService;

/**
 * ユーザー情報の操作.
 * 
 * @author hiranoyuusuke
 *
 */
@Controller
@RequestMapping("")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("")
	public String login() {
		return "login";
	}
	
	
	/**
	 * ログイン情報を受け取り、ログインをする.
	 * 
	 * @param form
	 * @param model
	 * @return メイン画面の表示
	 */
	@RequestMapping("/login")
	public String userLogin(UserForm form, Model model) {
		List<User> userList = userService.findUserForLogIn(form.getMailAddress(), form.getPassword());
		
		
		if(userList == null) {
			model.addAttribute("loginError", "ユーザー情報がありません");
			return login();
		}
		
		User user = userList.get(0);
		session.setAttribute("name", user.getName());
		return "main";
	}
	
	/**
	 * ユーザー登録.
	 * 
	 * @return ユーザー登録画面の表示.
	 */
	@RequestMapping("/userRegister")
	public String userregister() {
		return "register";
	}
	
	
	@RequestMapping("/insertUser")
	public String insertUser(UserForm form) {
		User user =  new User();
		user.setName(form.getName());
		user.setMailAddress(form.getMailAddress());
		user.setPassword(form.getPassword());
		userService.registerUserInfomation(user);
		return "main";
	}

}
