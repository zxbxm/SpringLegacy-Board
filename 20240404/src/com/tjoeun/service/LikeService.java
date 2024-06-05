package com.tjoeun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.LikeDAO;


/*
 * 구현하고자 하는 기능 
 * 1. 좋아요 추가 
 * 2. 좋아요 취소
 * 3. 
 */
@Service
public class LikeService {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public boolean checkLike(int user_idx) {
		return likeDAO.checkLike( user_idx);
	}
	
	public boolean likeUP(int content_idx) {
		try {
		likeDAO.likeUp(content_idx);
		System.out.println("좋아요 성공");
		return true; //좋아요 성공
	}catch(Exception e) {
		System.out.println("좋아요 실패");
		return false; //좋아요 실패
		}
	}
	
	public int likeCount(int content_idx) {
		return likeDAO.likeCount(content_idx);
	}
}

