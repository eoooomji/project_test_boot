package com.ugotfilm.data.service;

import com.ugotfilm.data.dto.GenreDTO;
import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.data.dto.PersonDTO;


public interface DataService {
	
	//정보 저장
	public int saveMovieProcess(MovieDTO data);
	public int savePersonProcess(PersonDTO data);
	public int saveGenreProcess(GenreDTO data);
	
	//클릭 저장
	public int choiceMovieProcess(int usercode, int moviecode);
	public int choicePersonProcess(int usercode, int personcode);
	public int choiceGenreProcess(int usercode, int genrecode);
	
}
