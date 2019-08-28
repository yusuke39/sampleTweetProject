package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

/**
 * ユーザー情報の操作.
 * 
 * @author hiranoyuusuke
 *
 */
@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * ユーザー情報を１件検索する.
	 * 
	 * @param mailAddress
	 * @param password
	 * @return　１件のユーザー情報を返す
	 */
	public List<User> findUserForLogIn(String mailAddress, String password) {
		List<User> userList = userRepository.userLogin(mailAddress, password);
		
		if(userList.size() == 0) {
			return null;
		}
		return userList;
	}
	
	/**
	 * ユーザー情報の登録.
	 * 
	 * @param user
	 */
	public void registerUserInfomation (User user) {
		userRepository.insert(user);
	}
	
}
