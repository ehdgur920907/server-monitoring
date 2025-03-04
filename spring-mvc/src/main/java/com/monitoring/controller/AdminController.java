package com.monitoring.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.monitoring.dto.AdminInformationDto;
import com.monitoring.mapper.Mapper;

@Controller
public class AdminController {
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping("/signin")
	public String signin() {
		return "/jsp/signin";
	}

	@RequestMapping("/signout")
	public String signout(HttpSession session) {
		session.removeAttribute("sessionedAdmin");
		return "redirect:/signin";
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String auth(String id, String pw, HttpSession session, Model model) {
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		AdminInformationDto adminInformationDto = mapper.selectAdminInformation(id);

		if (pw.equals(adminInformationDto.getPw())) {
			session.setAttribute("sessionedAdmin", adminInformationDto);
			return "redirect:/";
		} else {
			return "/jsp/signin";
		}
	}
}
