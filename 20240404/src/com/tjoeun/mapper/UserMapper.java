package com.tjoeun.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tjoeun.beans.UserBean;

public interface UserMapper {
	
	@Select("select user_name " + 
			    "from user_table " + 
			    "where user_id=#{user_id}")
	String checkUserIdExist(String user_id);
	
	@Insert("INSERT INTO user_table (user_idx, user_name, user_id, user_pw, user_area) " +
      "VALUES (user_seq.nextval, #{user_name}, #{user_id}, #{user_pw}, #{user_area})")
	void addUserInfo(UserBean joinUserBean);

	@Select("select user_idx, user_id, user_name, user_pw from user_table " + 
			    "where user_id=#{user_id} and user_pw=#{user_pw}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean);
	
	@Select("select user_name, user_id " + 
			    "from user_table " + 
			    "where user_idx = #{user_idx}")
	UserBean getModifyUserInfo(int user_idx);
	
	@Update("update user_table " + 
			    "set user_pw = #{user_pw} , user_area=#{user_area}" + 
			    "where user_idx = #{user_idx}")
	void modifyUserInfo(UserBean modifyUserBean);
	
	
}
