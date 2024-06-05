package com.tjoeun.beans;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CommentBean {
    private int comment_idx;
    

    private String comment_writer_id;
    @NotBlank
    private String comment_text;
    private String comment_date;
    private int comment_content_idx;

}
