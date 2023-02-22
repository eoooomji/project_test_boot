package com.ugotfilm.data.dto;

import java.util.Date;

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
public class MovieDTO {
	//가져오는 영화정보에 따라 세팅해야함
	// 필요한 영화 정보는 영화id, 타이틀, 원제, 포스터, 개봉년도, 장르, 감독, 배우는 필수
	// 영화 id를 이용해서 영화 detail에 접근
	// 타이틀, 원제, 포스터, 개봉년도 등은 마이페이지나 홈의 화면을 구성하는데      사용
	// 장르, 감독, 배우는 큐레이션에서 사용
	// 고민해봐야 할 점은 장르, 배우의 경우 배열로 들어오기때문에 어떤식으로 저장해서 검색할지 고민해봐야함
	private int moviecode ;
	private String title ;
	private Date release_date ;
	private String vote_average ;
	private String poster_url;
	
	private int count;
	private int id;
}
