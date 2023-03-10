package com.ugotfilm.data.service;

import java.util.List;

import com.ugotfilm.data.dto.GenreDTO;
import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.data.dto.PersonDTO;



public interface DataService {
	
	//정보 저장
	public int saveMovieProcess(MovieDTO data);
	public int saveCrewProcess(PersonDTO data);
	public List<PersonDTO> saveCastProcess();
	public List<GenreDTO> saveGenreProcess();
	
	//클릭 저장
	public int choiceMovieProcess(int usercode, int moviecode);
	public int choiceCastProcess(int usercode, int castcode);
	public int choiceCrewProcess(int usercode, int crewcode);
	public int choiceGenreProcess(int usercode, int genrecode);
	
}
