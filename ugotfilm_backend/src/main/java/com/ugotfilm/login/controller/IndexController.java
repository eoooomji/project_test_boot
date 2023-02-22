package com.ugotfilm.login.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ugotfilm.login.dto.UserDTO;
import com.ugotfilm.login.repository.IndexRepository;
import com.ugotfilm.login.service.UserService;

import oracle.jdbc.proxy.annotation.Post;

//@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class IndexController {

	@Autowired
	private BCryptPasswordEncoder encodePassword;

	@Autowired
	private IndexRepository UserRepository;
	
	@Autowired
	private UserService userService;

	public IndexController() {
		// TODO Auto-generated constructor stub
	}

	//회원 가입
	@PostMapping("/join")
	public String join(@RequestBody UserDTO user) {
		System.out.println(user.getPassword());
		user.setPassword(encodePassword.encode(user.getPassword()));
		UserRepository.saveUser(user);
		return "회원가입 완료";
	}
	
	//정보 수정
	
	//탈퇴(유저의 권한을 수정하는 형태)
	
	//아이디 중복 체크
	
	//닉네임 중복 체크
	
	// 유저 정보
	@PostMapping("/user")
	public UserDTO userInfo(UserDTO user) throws Exception{
		return userService.userProcess(user.getUsercode());
	}

}
