package com.tjoeun.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjoeun.beans.CommentBean;
import com.tjoeun.beans.ContentBean;
import com.tjoeun.beans.PageBean;
import com.tjoeun.beans.UserBean;
import com.tjoeun.service.BoardService;
import com.tjoeun.service.CommentService;
import com.tjoeun.service.LikeService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentService commentService;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model) {

		String boardName = boardService.getBoardInfoName(board_info_idx);
		List<ContentBean> contentList = boardService.getContentList(board_info_idx, page);
		PageBean pageBean = boardService.getContentCount(board_info_idx, page);

		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("boardName", boardName);
		model.addAttribute("contentList", contentList);
		model.addAttribute("page", page);
		model.addAttribute("pageBean", pageBean);

		return "board/main";
	}

	@GetMapping("/read")
	public String read(@RequestParam("board_info_idx") int board_info_idx, @RequestParam("content_idx") int content_idx,
			@RequestParam("page") int page,

			@ModelAttribute("writeCommentBean") CommentBean writeCommentBean, Model model) {

		ContentBean readContentBean = boardService.getContentInfo(content_idx);
		int likeCount = likeService.likeCount(content_idx);

		List<CommentBean> comments = commentService.getCommentsByContentIdx(content_idx);

		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);

		model.addAttribute("readContentBean", readContentBean);
		model.addAttribute("loginUserBean", loginUserBean);
		model.addAttribute("page", page);
		model.addAttribute("comments", comments);
		model.addAttribute("likeCount",likeCount);

		return "board/read";
	}

	@PostMapping("/comment_proc")
	public String addComment(@Valid @ModelAttribute("writeCommentBean") CommentBean writeCommentBean,
			BindingResult result, @RequestParam("content_idx") int content_idx,
			@RequestParam("board_info_idx") int board_info_idx, @RequestParam("page") int page, Model model) {
		// List<CommentBean> comments =
		// commentService.getCommentsByContentIdx(content_idx);
		model.addAttribute("page", page);
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		// model.addAttribute("comments", comments);

		if (result.hasErrors()) {

			return "board/comment_fail";
		}
		writeCommentBean.setComment_content_idx(content_idx);
		commentService.addComment(writeCommentBean);

		return "board/comment_success";
	}

	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentBean") ContentBean writeContentBean,
			@RequestParam("board_info_idx") int board_info_idx, @RequestParam("page") int page, Model model) {

		writeContentBean.setContent_board_idx(board_info_idx);
		model.addAttribute("page", page);

		return "board/write";
	}

	@PostMapping("/write_proc")
	public String write_proc(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean,
			BindingResult result) {

		if (result.hasErrors()) {
			return "board/write";
		}

		boardService.addContentInfo(writeContentBean);

		return "board/write_success";
	}

	// @RequestParam("board_info_idx") <-- 생략 가능함
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
			@RequestParam("board_info_idx") int board_info_idx, @RequestParam("content_idx") int content_idx,
			@RequestParam(value = "page") int page, Model model) {

		ContentBean tempModifyContentBean = boardService.getContentInfo(content_idx);

		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		model.addAttribute("page", page);
		modifyContentBean.setContent_writer_name(tempModifyContentBean.getContent_writer_name());
		modifyContentBean.setContent_date(tempModifyContentBean.getContent_date());
		modifyContentBean.setContent_subject(tempModifyContentBean.getContent_subject());
		modifyContentBean.setContent_text(tempModifyContentBean.getContent_text());
		modifyContentBean.setContent_file(tempModifyContentBean.getContent_file());
		modifyContentBean.setContent_writer_idx(tempModifyContentBean.getContent_writer_idx());
		modifyContentBean.setContent_board_idx(board_info_idx);
		modifyContentBean.setContent_idx(content_idx);

		return "board/modify";
	}

	@PostMapping("/modify_proc")
	public String modify_proc(@Valid @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
			BindingResult result, @RequestParam("page") int page, Model model) {
		if (result.hasErrors()) {
			return "board/modify";
		}

		boardService.modifyContentInfo(modifyContentBean);

		model.addAttribute("page", page);

		return "board/modify_success";
	}

	@GetMapping("/not_writer")
	public String not_writer() {
		return "board/not_writer";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam int board_info_idx, @RequestParam int content_idx, Model model) {

		boardService.deleteContentInfo(content_idx);
		model.addAttribute("board_info_idx", board_info_idx);

		return "board/delete";
	}

	@GetMapping("/comment_delete")
	public String comment_delete(@RequestParam int comment_idx, @RequestParam int content_idx,
			@RequestParam int board_info_idx, @RequestParam("page") int page,

			Model model) {
		commentService.deleteComment(comment_idx);
		model.addAttribute("content_idx", content_idx);
		model.addAttribute("comment_idx", comment_idx);
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("page", page);

		return "board/comment_delete";
	}

	@GetMapping("/search")
	public String searchContent(@RequestParam("keyword") String keyword,
			@RequestParam("board_info_idx") int boardInfoIdx, @RequestParam("page") int page, Model model) {
		RowBounds rowBounds = new RowBounds(0, 10); // 예시로 0부터 10까지의 결과만 보여줍니다.
		List<ContentBean> searchResult = boardService.searchContent(keyword, boardInfoIdx, rowBounds);

		model.addAttribute("searchResult", searchResult);
		model.addAttribute("boardInfoIdx", boardInfoIdx);
		model.addAttribute("page", page);
		return "board/search_result"; // 결과를 보여줄 뷰의 이름
	}
}
