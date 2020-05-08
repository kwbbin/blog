package com.kwbbin.Vo;

import com.kwbbin.bean.Comment;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CommentVo {
    Comment comment;
    Long dateLong;
    List<Comment> replay;
}
