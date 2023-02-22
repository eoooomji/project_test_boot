package com.ugotfilm.choice.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugotfilm.choice.dao.ChoiceDAO;
import com.ugotfilm.choice.service.ChoiceService;
import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.login.dao.UserDAO;
import com.ugotfilm.login.dto.UserDTO;
import com.ugotfilm.login.service.UserService;

import ch.qos.logback.core.pattern.parser.Parser;

/* 생각해볼 수 있는 예시
 * 
 * 로그인한 유저의 나이, 성별을 이용하여 추천하는 방법, 
ex) 20대 남자 회원이 로그인한 경우
20대 회원들이 가장 많이 본 영화 장르
남자 회원들이 가장 선호하는 감독의 작품리스트

ex)클릭한 기간도 저장되기 때문에 일정기간으로 한정하여 데이터를 뽑을 수 도 있다. (조회수 순위, 검색순위)

 
*/

@RestController
public class ChoiceController {

	@Autowired
	private ChoiceService choiceService;

	@Autowired
	private UserService userService;

	public ChoiceController() {

	}

	@GetMapping("/movie/best")
	public Map<String, Object> listMethod() {
		Map<String, Object> map = new HashMap<>();
		List<MovieDTO> aList = new ArrayList<>();
		map.put("aList", aList);
		return map;
	}// end listMethod()

	// 사용자 추천 리스트
	@PostMapping("/curation")
	public Map<String, Object> curationList(UserDTO user) throws Exception {
		
		// 유저 정보
		UserDTO userInfo = userService.userProcess(user.getUsercode());

		LocalDate date = LocalDate.now();
		int year = date.getYear();

//		System.out.println((((year - userInfo.getBirth()) + 1) / 10) * 10);

		// 성별 정보
		List<MovieDTO> genderInfo = choiceService.genderProcess(user.getUsercode());

		// 장르 정보
		List<MovieDTO> genreInfo = choiceService.genreProcess(user.getUsercode());

		Map<String, Object> map = new HashMap<>();
		map.put("gender", userInfo.getGender());
		map.put("genderInfo", genderInfo);
		map.put("genreInfo", genreInfo);

		return map;
	}// end genderList()

	// 장르별 선호 리스트

}// end class
