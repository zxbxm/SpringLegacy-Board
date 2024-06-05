package com.tjoeun.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.mapper.LikeMapper;

@Repository

public class LikeDAO {
	@Autowired
	private LikeMapper likeMapper;
	
	public boolean checkLike(int user_idx) {
		return likeMapper.checkLike(user_idx);				
	}
	public void likeUp(int content_idx) {
		likeMapper.likeUp(content_idx);
	}
	
	public int likeCount(int content_idx) {
		return likeMapper.likeCount(content_idx);
	}

}
