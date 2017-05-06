package com.monitoring.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitoring.dto.BasicInformationDto;
import com.monitoring.dto.ServerInformationDto;
import com.monitoring.mapper.Mapper;

@Controller
public class MonitoringController {
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping("/monitoring/{id}")
	public String getMonitoring(Locale locale, Model model, @PathVariable String id) {
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		BasicInformationDto basicInformationDto = mapper.selectBasicInformation(id)
				.get(mapper.selectBasicInformation(id).size() - 1);
		ServerInformationDto serverInformationDto = mapper.selectServerInformation(id);

		basicInformationDto.setRegisterDate(basicInformationDto.getRegisterDate().substring(0, 19));

		model.addAttribute("basicInformation", basicInformationDto);
		model.addAttribute("serverInformation", serverInformationDto);
		return "monitoring";
	}
	
	@RequestMapping("/detail/{id}")
	@ResponseBody
	public String monitoring(Model model, @PathVariable String id) {
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		BasicInformationDto basicInformationDto = mapper.selectBasicInformation(id)
				.get(mapper.selectBasicInformation(id).size() - 1);
		ServerInformationDto serverInformationDto = mapper.selectServerInformation(id);

		basicInformationDto.setRegisterDate(basicInformationDto.getRegisterDate().substring(0, 19));
		HashMap<String, String> hashMap = new HashMap<String, String>();
		
		hashMap.put("totalDisk", basicInformationDto.getTotalDisk());
		hashMap.put("usedDisk", basicInformationDto.getUsedDisk());
		hashMap.put("freeDisk", basicInformationDto.getFreeDisk());
		
		hashMap.put("totalMemory", basicInformationDto.getTotalMemory());
		hashMap.put("usedMemory", basicInformationDto.getUsedMemory());
		hashMap.put("freeMemory", basicInformationDto.getFreeMemory());
		
		hashMap.put("totalCpu", basicInformationDto.getTotalCpu());
		hashMap.put("userCpu", basicInformationDto.getUserCpu());
		hashMap.put("systemCpu", basicInformationDto.getSystemCpu());
		hashMap.put("idleCpu", basicInformationDto.getIdleCpu());
		
		hashMap.put("registerDate", basicInformationDto.getRegisterDate());
		
		hashMap.put("osName", serverInformationDto.getOsName());
		hashMap.put("ipAddress", serverInformationDto.getIpAddress());
		hashMap.put("hostName", serverInformationDto.getHostName());
		
		JSONObject json = new JSONObject();
		String jsonString = json.toJSONString(hashMap);
		return jsonString;
	}
}
