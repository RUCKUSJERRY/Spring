package com.mvc.update.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvc.update.model.biz.JDBCBiz;
import com.mvc.update.model.dto.JDBCDto;

@Controller
public class JDBCController {
	
	@Autowired
	private JDBCBiz biz;
	
	private Logger logger = LoggerFactory.getLogger(JDBCController.class);
	
	@RequestMapping("list.do")
	public String selectList(Model model) {
		logger.info("[Controller] : list.do");		
		model.addAttribute("list", biz.selectList());
		return "jdbclist";
	}
	@RequestMapping("select.do")
	public String selectOne(Model model, int seq) {
		logger.info("[Controller] : select.do");
		model.addAttribute("dto", biz.selectOne(seq));
		
		return "jdbcselect";
	}
	
	@RequestMapping("insert.do")
	public String insert() {
		logger.info("[Controller] : insert.do");
		return "jdbcinsert";
	}
	
	@RequestMapping(value = "/insertres.do", method = RequestMethod.POST)
	public String insertRes(Model model, String writer, String title, String content) {
		logger.info("[Controller] : insertres.do");
		
		JDBCDto dto = new JDBCDto(0, writer, title, content, null);
		
		int res = biz.insert(dto);
		
		if (res > 0) {
			return "redirect:list.do";
		} else {
			return "redirect:insert.do";
		}

	}
	
	@RequestMapping("update.do")
	public String update(Model model, int seq) {
		logger.info("[Controller] : update.do");
		model.addAttribute("dto", biz.selectOne(seq));
		
		return "jdbcupdate";
	}
	
	@RequestMapping(value = "/updateres.do", method = RequestMethod.POST)
	public String updateRes(Model model, String writer, String title, String content) {
		logger.info("[Controller] : updateres.do");
		
		JDBCDto dto = new JDBCDto(0, writer, title, content, null);
		
		int res = biz.insert(dto);
		
		if (res > 0) {
			return "redirect:list.do";
		} else {
			return "redirect:update.do";
		}

	}
	
}
