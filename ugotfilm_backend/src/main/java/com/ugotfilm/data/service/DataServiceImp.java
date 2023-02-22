package com.ugotfilm.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugotfilm.data.dao.DataDao;
import com.ugotfilm.data.dto.GenreDTO;
import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.data.dto.PersonDTO;

@Service
public class DataServiceImp implements DataService{

	@Autowired
	private DataDao dao;
	
	public DataServiceImp() {
	
	}
	
	@Override
	public int saveMovieProcess(MovieDTO data) {
		return dao.saveMovie(data);
	}

	@Override
	public int savePersonProcess(PersonDTO data) {
		return dao.savePerson(data);
	}

	@Override
	public int saveGenreProcess(GenreDTO data) {
		return dao.saveGenre(data);
	}

	@Override
	public int choiceMovieProcess(int usercode, int moviecode) {
		return dao.choiceMovie(usercode, moviecode);
	}

	@Override
	public int choicePersonProcess(int usercode, int personcode) {
		return dao.choicePerson(usercode, personcode);
	}

	@Override
	public int choiceGenreProcess(int usercode, int genrecode) {
		return dao.choiceGenre(usercode, genrecode);
	}

}
