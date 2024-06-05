package com.tjoeun.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjoeun.beans.UserBean;

@Controller
public class HomeController {
	
	@Resource(name="loginUserBean")
	private UserBean loginUserBean;

	@GetMapping("/")
	public String home(HttpServletRequest request,
										@RequestParam(name = "board_info_idx", required = false, defaultValue = "0")int board_info_idx) {
		
		System.out.println("loginUserBean IN session : " + loginUserBean);
		System.out.println("ContextPath");
		System.out.println(request.getServletContext().getRealPath("/"));
		
		return "redirect:/main";
	}
}
