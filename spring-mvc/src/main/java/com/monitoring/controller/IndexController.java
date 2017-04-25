package com.monitoring.controller;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monitoring.dto.ServerInformationDto;
import com.monitoring.mapper.Mapper;

@Controller
public class IndexController {
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/")
	public String memory(Model model) {
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		ArrayList<ServerInformationDto> arrayListServerInformation = new ArrayList<ServerInformationDto>();
	
		for (int i = 0; i < mapper.selectServerInformationList().size(); i++) {
			arrayListServerInformation.add(mapper.selectServerInformationList().get(i));
		}

		model.addAttribute("arrayListServerInformation", arrayListServerInformation);
		return "index";
	}
}
