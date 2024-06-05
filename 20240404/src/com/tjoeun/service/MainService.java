package com.tjoeun.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.beans.ContentBean;
import com.tjoeun.dao.BoardDAO;

@Service
public class MainService {
	
	@Autowired
	private BoardDAO boardDAO;
	
  public List<ContentBean> getMainList(int board_info_idx){
  	RowBounds rowBounds = new RowBounds(0, 5);
  	return boardDAO.getContentList(board_info_idx, rowBounds);
  }
  
}


