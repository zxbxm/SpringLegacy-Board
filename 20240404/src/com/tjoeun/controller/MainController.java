package com.tjoeun.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjoeun.beans.BoardInfoBean;
import com.tjoeun.beans.ContentBean;
import com.tjoeun.service.MainService;
import com.tjoeun.service.TopMenuService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private TopMenuService topMenuService;

	@GetMapping("/main")
	public String main(Model model,
			 @RequestParam(name = "board_info_idx", required = false, defaultValue = "0") int board_info_idx
			 ) {
		
		List<BoardInfoBean> boardInfoList = topMenuService.getTopMenuList();
		
		ArrayList<List<ContentBean>> boardsList = new ArrayList<List<ContentBean>>();
		
		for(int i = 1; i <= boardInfoList.size(); i++) {
		  List<ContentBean> boardList1 = mainService.getMainList(i);
		  boardsList.add(boardList1);
		}
		
		model.addAttribute("boardsList", boardsList);
		model.addAttribute("boardInfoList", boardInfoList);
		model.addAttribute("board_info_idx",board_info_idx);
		
		return "main";
	}     
}
  