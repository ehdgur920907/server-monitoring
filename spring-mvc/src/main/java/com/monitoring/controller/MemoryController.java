package com.monitoring.controller;

import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monitoring.dto.MemoryDto;
import com.monitoring.mapper.MemoryMapper;

@Controller
public class MemoryController {
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/memory")
	public String getMemory(Locale locale, Model model) {
		MemoryMapper memoryMapper = sqlSession.getMapper(MemoryMapper.class);
		MemoryDto memoryDto = memoryMapper.selectMemory().get(0);
		System.out.println(memoryDto.getTotalMemory());
		System.out.println(memoryDto.getUsedMemory());
		System.out.println(memoryDto.getFreeMemory());
		return "memory";
	}
}
