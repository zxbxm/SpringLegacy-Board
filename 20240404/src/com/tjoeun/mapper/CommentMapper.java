package com.tjoeun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;


import com.tjoeun.beans.CommentBean;


public interface CommentMapper {
	
	@SelectKey(statement="select comment_seq.nextval from dual", keyProperty="comment_idx", before=true, resultType=int.class)

    @Insert("INSERT INTO comment_table (comment_idx, comment_writer_id, comment_content_idx, comment_text, comment_date) " +
            "VALUES (#{comment_idx},  #{comment_writer_id}, #{comment_content_idx}, #{comment_text}, SYSDATE)")
    void addComment(CommentBean writeCommentBean);
    
    @Select("SELECT * FROM comment_table WHERE comment_content_idx = #{content_idx} order by comment_idx")
    List<CommentBean> getCommentsByContentIdx(int content_idx);
    
    
    @Delete("DELETE FROM comment_table WHERE comment_idx = #{comment_idx}")
    void deleteComment(int comment_idx);
    
    
    
}
