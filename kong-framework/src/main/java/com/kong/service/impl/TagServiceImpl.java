package com.kong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kong.domain.ResponseResult;
import com.kong.domain.dto.TagListDao;
import com.kong.domain.entity.Tag;
import com.kong.domain.vo.PageVo;
import com.kong.domain.vo.TagVo;
import com.kong.mapper.TagMapper;
import com.kong.service.TagService;
import com.kong.utils.BeanCopyUtils;
import com.kong.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2022-10-16 22:26:54
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDao tagListDao) {
        //分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(tagListDao.getName()),Tag::getName,tagListDao.getName());
        queryWrapper.like(StringUtils.hasText(tagListDao.getRemark()),Tag::getRemark,tagListDao.getRemark());


        Page<Tag> page = new Page<>(pageNum,pageSize);
        page(page, queryWrapper);
        //封装数据返回
        List<TagVo> listVo = BeanCopyUtils.copyBeanList(page.getRecords(),TagVo.class);
        PageVo pageVo = new PageVo(listVo,page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getName,Tag::getRemark,Tag::getId);
        List<Tag> list = list(queryWrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(list, TagVo.class);
        return ResponseResult.okResult(tagVos);
    }
}
