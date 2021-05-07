package com.boot.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.jdbc.model.biz.MyBoardBiz;

@Controller
@RequestMapping("/myboard")
public class MyBoardController {

	@Autowired
	private MyBoardBiz biz;
	
	@GetMapping("/list")
	public String selectList(Model model) {
		
		model.addAttribute("list", biz.selectList());
		
		return "myboardlist";
	}
	
	@GetMapping("/insertform")
	public String insertForm() {
		
		
		
		return "insertform";
		
	}

	
}
