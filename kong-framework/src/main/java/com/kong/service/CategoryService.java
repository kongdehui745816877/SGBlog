package com.kong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kong.domain.ResponseResult;
import com.kong.domain.entity.Category;
import com.kong.domain.vo.CategoryVo;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-10-06 15:32:13
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();

    ResponseResult getList(Integer pageNum, Integer pageSize, Category category);
}


