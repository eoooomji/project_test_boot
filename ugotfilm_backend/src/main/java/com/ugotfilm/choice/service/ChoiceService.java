package com.ugotfilm.choice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.notice.dto.BoardDTO;
import com.ugotfilm.notice.dto.PageDTO;
import com.ugotfilm.login.dto.UserDTO;


@Service
public interface ChoiceService {
	//dao보고 작성필요
	public int countProcess();
	public List<BoardDTO> listProcess(PageDTO pv);
	public void insertProcess(BoardDTO dto);
	public BoardDTO contentProcess(int num);
	public void reStepProcess(BoardDTO dto);
	public BoardDTO updateSelectProcess(int num);
	public void updateProcess(BoardDTO dto, String urlpath);
	public void deleteProcess(int num, String urlpath);
	public String fileSelectprocess(int num);
	
	public List<MovieDTO> genderProcess(int num) throws Exception; 
	public List<MovieDTO> genreProcess(int num) throws Exception;
}
