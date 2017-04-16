package com.monitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.information.get.Get;
import com.information.get.GetMemory;

@Controller
public class MemoryController {
	Get get;
	@RequestMapping("/memory")
	public String memory(Model model) {
		get = new GetMemory();
		get.execute(model);
		return "memory";
	}
}
