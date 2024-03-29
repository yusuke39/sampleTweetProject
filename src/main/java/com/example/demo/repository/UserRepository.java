package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

@Repository
public class UserRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private final static RowMapper<User> USER_ROW_MAPPER = (rs,i) ->{
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setMailAddress(rs.getString("mail_address"));
		user.setPassword(rs.getString("password"));
		return user;
	};
	
	/**
	 * ユーザー情報を１件取得する.
	 * 
	 * @param mailAddress
	 * @param password
	 * @return　１件のユーザー情報を返します
	 */
	public List<User> userLogin(String mailAddress, String password) {
		String sql = "SELECT id,name,mail_address,password FROM users WHERE mail_address = :mailAddress AND password = :password";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		
		return userList;
		
	}
	
	/**
	 * ユーザー情報を登録します.
	 * 
	 * @param user
	 */
	public void insert(User user) {
		String sql = "INSERT INTO users(name,mail_address,password) VALUES(:name, :mailAddress, :password)";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", user.getName()).addValue("mailAddress", user.getMailAddress()).addValue("password", user.getPassword());
		
		template.update(sql, param);
	}

}
