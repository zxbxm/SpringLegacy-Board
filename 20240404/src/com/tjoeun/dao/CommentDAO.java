package com.tjoeun.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.beans.CommentBean;
import com.tjoeun.mapper.CommentMapper;

@Repository
public class CommentDAO {
	
	@Autowired
	private CommentMapper commentMapper;
	
    public void addComment(CommentBean writecommentBean) {
		commentMapper.addComment(writecommentBean);
	}
    
    public List<CommentBean> getCommentsByContentIdx(int content_idx){
    	return commentMapper.getCommentsByContentIdx(content_idx);
    }
    

    
    public void deleteComment(int comment_idx) {
    	commentMapper.deleteComment(comment_idx);
    }
    
    
    
}
