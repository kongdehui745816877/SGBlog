package com.kong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kong.domain.ResponseResult;
import com.kong.domain.entity.Link;
import com.kong.domain.vo.LinkManagerVo;
import com.kong.domain.vo.PageVo;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-10-08 14:36:53
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    PageVo selectLinkPage(Integer pageNum, Integer pageSize, Link link);
}


