package com.kong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kong.domain.ResponseResult;
import com.kong.domain.dto.AddArticleDto;
import com.kong.domain.dto.ArticleDto;
import com.kong.domain.entity.Article;
import com.kong.domain.vo.ArticleVo;
import com.kong.domain.vo.PageVo;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto addArticleDto);

    PageVo selectArticlePage(Article article, Integer pageNum, Integer pageSize);

    ArticleVo getInfo(Long id);

    void edit(ArticleDto articleDto);
}
