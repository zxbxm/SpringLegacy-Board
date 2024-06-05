package com.tjoeun.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.tjoeun.beans.ContentBean;

// 게시글 검색할 때 사용하는 인터패스
public interface BoardSearchService {
    List<ContentBean> searchContent(String keyword, int board_info_idx, RowBounds rowBounds);
}
