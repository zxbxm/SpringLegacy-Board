package com.tjoeun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.tjoeun.beans.ContentBean;

@Mapper
public interface BoardMapper {
		
	
	// content_seq 값 가져오기
	@SelectKey(statement="select content_seq.nextval from dual", keyProperty="content_idx", before=true, resultType=int.class)

	
	// 글쓰기한 내용을 DB 에 저장함
	@Insert("insert into content_table (content_idx, content_subject, content_text, " +
      "content_file, content_writer_idx, content_board_idx, content_date) " + 
      "values(#{content_idx}, #{content_subject}, #{content_text}, " +
      "#{content_file}, #{content_writer_idx}, #{content_board_idx}, sysdate)")

			void addContentInfo(ContentBean writeContentBean); 
	
	// board_info_idx 로 board_info_name 을 select 함
	@Select("select board_info_name " + 
		    "from board_info_table " + 
		    "where board_info_idx=#{board_info_idx}")
			String getBoardInfoName(int board_info_idx); 
	
	
	// board/main.jsp  글번호	제목	작성자 작성날짜   DB에서 select
	@Select("select c.content_idx, c.content_subject, u.user_name content_writer_name, " + 
      "to_char(c.content_date, 'YYYY-MM-DD') content_date " + 
      "from content_table c, user_table u " + 
      "where c.content_writer_idx = u.user_idx " + 
      "and c.content_board_idx = #{board_info_idx} " + 
      "order by c.content_idx desc ")
	List<ContentBean> getContentList(int board_info_idx, RowBounds rowBounds); 

	
	
	// 작성자, 작성날짜, 제목, 내용, 파일
	@Select("select u.user_name content_writer_name, " + 
		    "to_char(c.content_date, 'YYYY-MM-DD') content_date, " + 
		    "c.content_subject, c.content_text, c.content_file, c.content_writer_idx " + 
		    "from content_table c, user_table u " + 
		    "where c.content_writer_idx = u.user_idx " + 
		    "and c.content_idx = #{c.content_idx}")
			ContentBean getContentInfo(int content_idx);
	
	// 글 수정하기
		@Update("update content_table " + 
				"set content_subject=#{content_subject}, content_text=#{content_text}, " + 
				"content_file=#{content_file, jdbcType=VARCHAR} " + 
				"where content_idx=#{content_idx}")
		void modifyContentInfo(ContentBean modifyContentBean);
		
		// 글 삭제하기
		@Delete("delete from content_table " + 
				"where content_idx=#{content_idx}")
		void deleteContentInfo(int content_idx);
		
		// 각 게시판(번호(1, 2, 3, 4 중 하나))에 해당하는 게시글 개수 조회하기
		//  ㄴ 각 게시판 별 게시글 개수
		@Select("select count(*) from content_table " + 
				"where content_board_idx=#{content_board_idx}")
		int getContentCount(int content_board_idx);
		
		// 특정 키워드에 대한 검색을 수행하여 결과를 반환하는 메서드
		@Select("SELECT c.*, u.user_name as content_writer_name " +
        "FROM content_table c " +
        "JOIN user_table u ON c.content_writer_idx = u.user_idx " +
        "WHERE c.content_subject LIKE '%' || #{keyword} || '%' " +
        "AND c.content_board_idx = #{boardInfoIdx} " +
        "ORDER BY c.content_idx DESC")
		List<ContentBean> searchContent(@Param("keyword") String keyword, @Param("boardInfoIdx") int boardInfoIdx, RowBounds rowBounds);
		
		// getContent 메서드 추가
    @Select("select * from content_table where content_idx=#{contentIdx}")
    ContentBean getContent(@Param("contentIdx") int contentIdx);
	}
  

