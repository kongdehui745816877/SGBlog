package com.kong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kong.domain.ResponseResult;
import com.kong.domain.dto.TagListDao;
import com.kong.domain.entity.Tag;
import com.kong.domain.vo.PageVo;
import com.kong.domain.vo.TagVo;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2022-10-16 22:26:54
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDao tagListDao);

    ResponseResult<TagVo> listAllTag();
}


