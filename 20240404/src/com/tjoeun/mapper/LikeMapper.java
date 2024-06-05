package com.tjoeun.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface LikeMapper {
	
	//좋아요 눌렀는지 안눌렀는지 먼저 확인하기
	@Select("select count(*) from like_table " +
			"where user_idx = #{user_idx}")
	boolean checkLike(int user_idx);
	
	//좋아요 카운트업 시키기 
	@Update("UPDATE like_table SET like_count = like_count + 1 WHERE content_idx = #{content_idx}")
    void likeUp(int content_idx);
	
	//좋아요 개수 가져오기 
	@Select ("select like_count from like_table where content_idx =#{content_table}")
	int likeCount(int content_idx);
}
