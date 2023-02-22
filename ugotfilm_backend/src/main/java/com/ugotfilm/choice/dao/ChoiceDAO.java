package com.ugotfilm.choice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.login.dto.UserDTO;

@Repository
@Mapper
public interface ChoiceDAO {


	int exist(int moviecode);
//	List<MovieDTO> genre(int usercode);
	List<MovieDTO> readcount(int usercode);
	List<MovieDTO> totalreadcount();
	
	List<MovieDTO> gender(int usercode) throws Exception;
	
	List<MovieDTO> genre(int usercode) throws Exception;
}
