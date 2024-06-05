package com.tjoeun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjoeun.beans.ContentBean;
import com.tjoeun.service.BoardService;
import com.tjoeun.service.LikeService;

@Controller
@RequestMapping("/board")
public class LikeController {

	@Autowired
	private LikeService likeService;
	
	@Autowired
	private BoardService boardService;

	@GetMapping("/like")
	public String like(@ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
					   @RequestParam("board_info_idx") int board_info_idx, 
					   @RequestParam("content_idx") int content_idx,
					    @RequestParam("page")int page,
						Model model) {

		// 좋아요 여부를 확인하기 위한 코드 > 좋아요 여부에 따라 좋아요 누르기 , 취소 액션으로 이어짐
		//boolean isLiked = likeService.checkLike(user_idx);
		
		// 좋아요 기능 동작 후 성공여부를 반환하는 코드
		boolean likeSuccess = likeService.likeUP(content_idx);
		
		
		ContentBean tempModifyContentBean = boardService.getContentInfo(content_idx);
		int likeCount = likeService.likeCount(content_idx);
		
		modifyContentBean.setContent_writer_name(tempModifyContentBean.getContent_writer_name());
	    modifyContentBean.setContent_date(tempModifyContentBean.getContent_date());
	    modifyContentBean.setContent_subject(tempModifyContentBean.getContent_subject());
	    modifyContentBean.setContent_text(tempModifyContentBean.getContent_text());
	    modifyContentBean.setContent_file(tempModifyContentBean.getContent_file());
	    modifyContentBean.setContent_writer_idx(tempModifyContentBean.getContent_writer_idx());
	    modifyContentBean.setContent_board_idx(board_info_idx);
	    modifyContentBean.setContent_idx(content_idx);
		

		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		//model.addAttribute("user_idx",user_idx);
		model.addAttribute("page",page);		
		//model.addAttribute("isLiked", isLiked);
		model.addAttribute("likeSuccess", likeSuccess);
		model.addAttribute("likeCount",likeCount);
		
	

		return "board/like";

	}

}
