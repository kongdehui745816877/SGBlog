package com.kong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kong.constants.SystemConstants;
import com.kong.domain.ResponseResult;
import com.kong.domain.entity.Comment;
import com.kong.domain.vo.CommentVo;
import com.kong.domain.vo.PageVo;
import com.kong.enums.AppHttpCodeEnum;
import com.kong.exception.SystemException;
import com.kong.mapper.CommentMapper;
import com.kong.service.CommentService;
import com.kong.service.UserService;
import com.kong.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-10-13 15:01:56
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        //对articleId进行判断
        LambdaQueryWrapper<Comment> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId);

        //根评论 rootId为-1
        queryWrapper.eq(Comment::getRootId, SystemConstants.ROOT_ID);

        //评论类型
        queryWrapper.eq(Comment::getType,commentType);

        //分页查询
        Page<Comment> page=new Page<>(pageNum,pageSize);
        page(page,queryWrapper);

        List<CommentVo> commentVoList= toCommentVoList(page.getRecords());

        //查询所有根评论对应的子评论集合，并且赋值给对应的属性
        for (CommentVo commentVo : commentVoList) {
            //查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children);
        }
        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        //评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    /**
     * 根据根评论id查询所对应的子评论的集合
     * @param id 根评论id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);

        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;
    }

    private List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //遍历vo集合
        commentVos=commentVos.stream()
                .map( o ->{
                    //通过CreateBy查询用户的昵称并赋值
                    String nickName = userService.getById(o.getCreateBy()).getNickName();
                    o.setUsername(nickName);
                    //如果toCommentUserId不为-1才进行查询
                    if(o.getToCommentUserId() != -1){
                        String toCommentUserName = userService.getById(o.getToCommentUserId()).getNickName();
                        o.setToCommentUserName(toCommentUserName);
                    }
                    return o;
                }).collect(Collectors.toList());
        //通过CreateBy查询用户的昵称并赋值
        //通过toCommentUserId查询用户的昵称并赋值
        return commentVos;
    }
}
