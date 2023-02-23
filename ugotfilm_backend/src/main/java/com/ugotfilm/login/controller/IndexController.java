package com.ugotfilm.login.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ugotfilm.login.dto.UserDTO;
import com.ugotfilm.login.repository.IndexRepository;

//@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class IndexController {

	@Autowired
	private BCryptPasswordEncoder encodePassword;

	@Autowired
	private IndexRepository UserRepository;


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
	
	//회원 정보 가져오기
	@PostMapping("/update")
	public String userInfo(@RequestBody UserDTO user) {
		System.out.println("정보 수정(유저코드) : " + user.getUsercode());
		
		user = UserRepository.userInfo(user);
		System.out.println("가져온 유저 정보 : " + user.toString());
		return "회원정보 가져옴";
	}
	//탈퇴(유저의 권한을 수정하는 형태)

	//아이디 중복 체크
	
	//닉네임 중복 체크

}
