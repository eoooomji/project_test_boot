package com.ugotfilm.choice.dto;

import java.sql.Date;

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
public class ChoiceDTO {
	private int usercode;
	
	private int moviecode;
	
	private int personcode;
	
	private int genrecode;
	
	private Date choicedate;
}
