package com.ugotfilm.mypage.service;

import java.util.List;

import com.ugotfilm.choice.dto.ChoiceDTO;
import com.ugotfilm.data.dto.GenreDTO;
import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.data.dto.PersonDTO;
import com.ugotfilm.login.dto.UserDTO;


public interface MypageService {
	//유저가 선호하는 감독 리스트
	public List<PersonDTO> bestDirector(UserDTO user) throws Exception;
	//유저가 선호나는 배우 리스트
	public List<PersonDTO> bestCast(UserDTO user) throws Exception;
	//wordcloud
	public List<GenreDTO> wordcloudProcess(int usercode);
	
	

	public List<GenreDTO> bestGenderGenre(UserDTO user) throws Exception;
	public List<MovieDTO> bestGenderMovie(UserDTO user) throws Exception;
	public List<PersonDTO> bestGenderDirector(UserDTO user) throws Exception;
	public List<PersonDTO> bestGenderCast(UserDTO user) throws Exception;
	
}
