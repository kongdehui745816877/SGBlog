package com.kong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kong.domain.ResponseResult;
import com.kong.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-10-13 15:01:55
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}


