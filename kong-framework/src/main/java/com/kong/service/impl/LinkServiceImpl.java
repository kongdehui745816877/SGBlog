package com.kong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kong.constants.SystemConstants;
import com.kong.domain.ResponseResult;
import com.kong.domain.entity.Link;
import com.kong.domain.vo.LinkManagerVo;
import com.kong.domain.vo.LinkVo;
import com.kong.domain.vo.PageVo;
import com.kong.domain.vo.TagVo;
import com.kong.mapper.LinkMapper;
import com.kong.service.LinkService;
import com.kong.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2022-10-08 14:36:54
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    /**
     * 查询所有审核通过的友链
     * @return
    @Override
     */
    public ResponseResult getAllLink() {
        //查询所有通过的友链
        LambdaQueryWrapper<Link> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //转换成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        //封装返回
        return ResponseResult.okResult(linkVos);
    }

    @Override
    public PageVo selectLinkPage(Integer pageNum, Integer pageSize, Link link) {
        LambdaQueryWrapper<Link> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(link.getName()),Link::getName,link.getName());
        queryWrapper.like(StringUtils.hasText(link.getStatus()),Link::getStatus,link.getStatus());


        Page<Link> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);

        //封装数据返回
        List<LinkManagerVo> listVo = BeanCopyUtils.copyBeanList(page.getRecords(),LinkManagerVo.class);
        PageVo pageVo = new PageVo(listVo,page.getTotal());

        return pageVo;

    }
}
