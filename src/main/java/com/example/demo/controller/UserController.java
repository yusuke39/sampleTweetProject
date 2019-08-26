package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	/**
	 * ユーザー登録.
	 * 
	 * @return ユーザー登録画面の表示.
	 */
	@RequestMapping("")
	public String userregister() {
		return "register";
	}
	
	
	@RequestMapping("/userRegister")
	public String userInfomation(UserForm form) {
		User user =  new User();
		user.setName(form.getName());
		user.setMailAddress(form.getMailAddress());
		user.setPassword(form.getPassword());
		userService.registerUserInfomation(user);
		return "main";
	}

}
