package com.mvc.upgrade.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvc.upgrade.model.biz.MYBoardBiz;
import com.mvc.upgrade.model.dto.MYBoardDto;

@Controller
public class MYBoardController {

	@Autowired
	private MYBoardBiz biz;
	
	@RequestMapping("/list.do")
	public String selectList(Model model) {
		model.addAttribute("list", biz.selectList());
		
		return "myboardlist";
	}
	
	@RequestMapping("/select.do")
	public String selectOne(Model model, int myno) {
		model.addAttribute("dto", biz.selectOne(myno));
		
		return "myboardselect";
	}
	
	@RequestMapping("/insert.do")
	public String insert(Model model) {
		model.addAttribute("list", biz.selectList());
		
		return "myboardinsert";
	}
	
	@RequestMapping(value = "/insertres.do", method = RequestMethod.POST)
	public String insertres(Model model, String myname, String mytitle, String mycontent) {
		
		MYBoardDto dto = new MYBoardDto();
		dto.setMyname(myname);
		dto.setMytitle(mytitle);
		dto.setMycontent(mycontent);
		
		int res = biz.insert(dto);
		
		if (res > 0) {
			System.out.println("입력 성공");
			model.addAttribute("list", biz.selectList());
			return "myboardlist";
		} else {
			System.out.println("입력 실패");
			return "myboardinsert";
		}
	}
	
	@RequestMapping("/update.do")
	public String update(Model model, int myno) {
		
		model.addAttribute("dto", biz.selectOne(myno));
		
		return "myboardupdate";
	}
	
	@RequestMapping(value = "/updateres.do", method = RequestMethod.POST)
	public String updateres(Model model, int myno, String mytitle, String mycontent) {
		
		int res = biz.update(new MYBoardDto(myno, null, mytitle, mycontent, null));
		
		if (res > 0) {
			System.out.println("수정 성공");
			model.addAttribute("dto", biz.selectOne(myno));
			return "myboardselect";
		} else {
			System.out.println("수정 실패");
			model.addAttribute("dto", biz.selectOne(myno));
			return "myboardselect";
		}
		
		
	}
	
	@RequestMapping("/delete.do")
	public String delete(Model model, int myno) {
		int res = biz.delete(myno);
		
		if (res > 0) {
			System.out.println("삭제 성공");
			model.addAttribute("list", biz.selectOne(myno));
			return "myboardselect";
		} else {
			System.out.println("삭제 실패");
			model.addAttribute("dto", biz.selectOne(myno));
			return "myboardselect";
		}
	}
	
	
	
}
