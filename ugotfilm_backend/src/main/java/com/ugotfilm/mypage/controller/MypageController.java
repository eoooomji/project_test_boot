package com.ugotfilm.mypage.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugotfilm.choice.dto.ChoiceDTO;
import com.ugotfilm.data.dto.GenreDTO;
import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.data.dto.PersonDTO;
import com.ugotfilm.login.dto.UserDTO;
import com.ugotfilm.login.repository.IndexRepository;
import com.ugotfilm.mypage.service.MypageService;

@RestController
public class MypageController {
	
	@Autowired
	private MypageService service;
	
	@Autowired
	private IndexRepository UserRepository;
	
	public MypageController() {
	
	}

	//마이페이지에 접근하면 한번에 정보를 저장하여 프론트로 보내준다.
	@PostMapping("/mypage")
	public Map<String, Object> mypageMethod(UserDTO user) throws Exception{
		System.out.println("마이페이지 시작(usercode) :"+user.getUsercode() );
		//유저 정보 불러오기
		user = UserRepository.userInfo(user);
		
		//프론트로 보낼 정보 세팅
		Map<String, Object> map = new HashMap<>();
			map.put("bestDirector", bestDirector(user));
			map.put("bestCast", bestCast(user));
			map.put("wordcloud", getWordcloudMethod(user.getUsercode()));
		return map;
	}//end mypageMethod()

	//유저가 선호하는 감독 리스트
	public List<PersonDTO> bestDirector(UserDTO user) throws Exception {
		return service.bestDirector(user);
	}
	//유저가 선호하는 배우리스트
	public List<PersonDTO> bestCast(UserDTO user) throws Exception {
		return service.bestCast(user);
	}
	
	//기타
	
	//워드클라우드용 테스트
	@GetMapping("/mypage/wordcloud/{usercode}")
	public Map<String, Object> wordcloudMethod(@PathVariable("usercode") int usercode) {
		
		System.out.println("usercode :" + usercode);
		Map<String, Object> map = new HashMap<>();
		List<GenreDTO> aList = service.wordcloudProcess(usercode);
		System.out.println("결과 :" +  aList.toString());
		map.put("aList", aList);
		return map;
	}//end listMethod()
	
	//워드클라우드용 테스트
	public List<GenreDTO> getWordcloudMethod(int usercode) {
		return service.wordcloudProcess(usercode);
	}//end listMethod()
	
	
	// 로그인한 유저의 성별을 사용하여 전체 회원의 선호 장르
	public List<GenreDTO> bestGenderGenre(UserDTO user) throws Exception {
		return service.bestGenderGenre(user);
	}
	
	// 로그인한 유저의 성별을 사용하여 전체 회원의 선호 영화
	public List<MovieDTO> bestGenderMovie(UserDTO user) throws Exception {
		return service.bestGenderMovie(user);
	}
	
	// 로그인한 유저의 성별을 사용하여 전체 회원의 선호 감독
	public List<PersonDTO> bestGenderDirector(UserDTO user) throws Exception {
		return service.bestGenderDirector(user);
	}
	
	// 로그인한 유저의 성별을 사용하여 전체 회원의 선호 배우
	public List<PersonDTO> bestGenderCast(UserDTO user) throws Exception {
		return service.bestGenderCast(user); 
	}
	


	
}//end class
