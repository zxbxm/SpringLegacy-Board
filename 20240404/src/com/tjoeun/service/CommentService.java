package com.tjoeun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.beans.CommentBean;
import com.tjoeun.beans.UserBean;
import com.tjoeun.dao.CommentDAO;

@Service
public class CommentService {
    @Autowired
    private CommentDAO commentDAO;
    
    @Autowired
    private UserBean loginUserbean;
    
    public void addComment(CommentBean writecommentBean) {
    	
    	writecommentBean.setComment_writer_id(loginUserbean.getUser_id());
    	
        commentDAO.addComment(writecommentBean);
    }
    
    public List<CommentBean> getCommentsByContentIdx(int content_idx) {
        return commentDAO.getCommentsByContentIdx(content_idx);
    }
    

    
    public void deleteComment(int comment_idx) {
        commentDAO.deleteComment(comment_idx);
    }
}
