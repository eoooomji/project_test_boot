package com.ugotfilm.choice.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugotfilm.choice.dto.ChoiceDTO;
import com.ugotfilm.choice.service.ChoiceService;
import com.ugotfilm.data.dto.GenreDTO;
import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.data.dto.PersonDTO;
import com.ugotfilm.login.dto.UserDTO;
import com.ugotfilm.login.repository.IndexRepository;

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
	private ChoiceService service;

	@Autowired
	private IndexRepository UserRepository;

	public ChoiceController() {

	}

	// 사용자 추천 리스트
	@PostMapping("/curation")
	public Map<String, Object> curationList(UserDTO user) throws Exception {
		System.out.println("큐레이션 시작(usercode) :" + user.getUsercode());
		// 유저 정보 가져옴
		user = UserRepository.userInfo(user);

		Map<String, Object> map = new HashMap<>();
		map.put("basic_curation", basicCuration(user));
		map.put("choice_curation", curation(user));

		return map;
	}// end genderList()

	// 전체 회원 기준 큐레이션
	public Map<String, Object> basicCuration(UserDTO user) throws Exception {

		// 프론트로 보낼 정보 세팅
		Map<String, Object> map = new HashMap<>();
		// 기준 정보(연령대, 성별)
		map.put("choice", userChoiceInfo(user));

		// 전체 기준 가장 많이 클릭한 장르, 영화리스트, 감독, 배우
		map.put("bestMovie", bestMovie());
		map.put("bestGenre", bestGenre());
		map.put("bestDirector", bestDirector());
		map.put("bestCast", bestCast());

		return map;
	}

	// 사용자 기반 큐레이션
	public Map<String, Object> curation(UserDTO user) throws Exception {

		Map<String, Object> map = new HashMap<>();

		int num = (int) ((Math.random() * 10000) % 10);
		if (num % 2 == 0) {
			System.out.println("짝" + num);
			// 로그인한 유저 성별 기준 가장 많이 클릭한 장르, 영화 리스트, 감독, 배우
			map.put("CurationMovie", bestGenderMovie(user));
			map.put("CurationGenre", bestGenderGenre(user));
			map.put("CurationDirector", bestGenderDirector(user));
			map.put("CurationCast", bestGenderCast(user));
		} else {
			System.out.println("홀" + num);
			// 로그인한 유저 연령대 기준 가장 많이 클릭한 장르, 영화 리스트, 감독, 배우
			map.put("CurationMovie", bestBirthMovie(user));
			map.put("CurationGenre", bestBirthGenre(user));
			map.put("CurationDirector", bestBirthDirector(user));
			map.put("CurationCast", bestBirthCast(user));
		}
		return map;
	}

	// 연령대 계산
	public ChoiceDTO userChoiceInfo(UserDTO user) throws Exception {
		// 1. 연령대를 구한다 like 10대, 20대
		LocalDate date = LocalDate.now();
		int year = date.getYear();
		int ageGroup = ((year - user.getBirth()) / 10) * 10;
		int max = year - ageGroup - 9;
		int min = year - ageGroup;

		// 2. dto에 담아 리턴한다
		ChoiceDTO dto = new ChoiceDTO();
		dto.setMax(max);
		dto.setMin(min);

		// 3. 연령대 정보 저장
		dto.setAgeGroup(ageGroup);

		// 4. 성별 정보 저장
		dto.setGender(user.getGender());
		return dto;
	}

	// 큐레이션
	// 로그인한 유저의 성별을 사용하여 전체 회원의 선호 장르
	public GenreDTO bestGenderGenre(UserDTO user) throws Exception {
		return service.bestGenderGenre(user);
	}

	// 로그인한 유저의 성별을 사용하여 전체 회원의 선호 영화
	public List<MovieDTO> bestGenderMovie(UserDTO user) throws Exception {
		return service.bestGenderMovie(user);
	}

	// 로그인한 유저의 성별을 사용하여 전체 회원의 선호 감독
	public PersonDTO bestGenderDirector(UserDTO user) throws Exception {
		return service.bestGenderDirector(user);
	}

	// 로그인한 유저의 성별을 사용하여 전체 회원의 선호 배우
	public PersonDTO bestGenderCast(UserDTO user) throws Exception {
		return service.bestGenderCast(user);
	}

	// 로그인한 유저의 연령대를 사용하여 전체 회원의 같은 연령대 사람들의 선호 장르
	public GenreDTO bestBirthGenre(UserDTO user) throws Exception {
		return service.bestBirthGenre(userChoiceInfo(user));
	}

	// 로그인한 유저의 연령대를 사용하여 전체 회원의 같은 연령대 사람들의 선호 영화
	public List<MovieDTO> bestBirthMovie(UserDTO user) throws Exception {
		return service.bestBirthMovie(userChoiceInfo(user));
	}

	// 로그인한 유저의 연령대를 사용하여 전체 회원의 같은 연령대 사람들의 선호 감독
	public PersonDTO bestBirthDirector(UserDTO user) throws Exception {
		return service.bestBirthDirector(userChoiceInfo(user));
	}

	// 로그인한 유저의 연령대를 사용하여 전체 회원의 같은 연령대 사람들의 선호 배우
	public PersonDTO bestBirthCast(UserDTO user) throws Exception {
		return service.bestBirthCast(userChoiceInfo(user));
	}

	// 로그인한 유저의 연령대를 사용하여 전체 회원의 같은 연령대 사람들의 선호 장르
	public GenreDTO bestGenre() throws Exception {
		return service.bestGenre();
	}

	// 로그인한 유저의 연령대를 사용하여 전체 회원의 같은 연령대 사람들의 선호 영화
	public List<MovieDTO> bestMovie() throws Exception {
		return service.bestMovie();
	}

	// 로그인한 유저의 연령대를 사용하여 전체 회원의 같은 연령대 사람들의 선호 감독
	public PersonDTO bestDirector() throws Exception {
		return service.bestDirector();
	}

	// 로그인한 유저의 연령대를 사용하여 전체 회원의 같은 연령대 사람들의 선호 배우
	public PersonDTO bestCast() throws Exception {
		return service.bestCast();
	}
}// end class
