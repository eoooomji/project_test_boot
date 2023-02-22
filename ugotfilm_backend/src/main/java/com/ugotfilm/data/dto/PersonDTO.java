package com.ugotfilm.data.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDTO {
	//가져오는 인물정보에 따라 세팅해야함
	// 필요한 영화 정보는 인물id, 이름, 인물 대표 이미지, 직잭(?) 등등
	// 인물id를 이용해서 인물 detail에 접근 -> tmdb 인물검색결과를 참고
	// 이름, 인물 대표이미지는 마이페이지나 홈의 화면을 구성하는데 사용
	// 고민해봐야 할 점은 아직 정확하게 무엇을 들고 올 수 있는지 모름
	private int personcode;
	private String name;
	private String poster_url;
	private String position;
}
